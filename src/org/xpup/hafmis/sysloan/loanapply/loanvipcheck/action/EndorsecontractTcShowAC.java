package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;

/**
 * @author 王野 2007-10-09
 */
public class EndorsecontractTcShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action.EndorsecontractTcShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    Pagination pagination = null;
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      pagination = getPagination(PAGINATION_KEY, request);
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      String pl122IdWY = request.getParameter("pl122IdWY");// pl121 ID
      // 当双击列表时，获取pl122主键ID，进行查询
      if (pl122IdWY != null && !pl122IdWY.equals("")) {
        endorsecontractTcAF = endorsecontractBS.updateImpawnContract(pl122IdWY,
            pagination, securityInfo, request);
      } else {
        // 默认进入列表时，显示列表中ID最大的记录
        String contractId = (String) request.getSession().getAttribute(
            "contractIdWY");
        endorsecontractTcAF = endorsecontractBS.queryImpawnContractList_(
            contractId, pagination, securityInfo, request);
        List list = endorsecontractTcAF.getList();
        request.getSession().setAttribute("loanvipcheckTcList", list);
      }
      List loanvipcheckTcList = (List) request.getSession().getAttribute(
          "loanvipcheckTcList");
      if (loanvipcheckTcList != null && !loanvipcheckTcList.equals("")) {
        endorsecontractTcAF.setList(loanvipcheckTcList);
      }
      // 枚举转换证件类型
      String cardKind = "";
      if (endorsecontractTcAF.getCardKind() != null
          && !"".equals(endorsecontractTcAF.getCardKind())) {
        cardKind = BusiTools.getBusiValue(Integer.parseInt(endorsecontractTcAF
            .getCardKind()), BusiConst.DOCUMENTSSTATE);
      }
      endorsecontractTcAF.setCardKind(cardKind);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("theEndorsecontractTcAF", endorsecontractTcAF);
    return mapping.findForward("to_loanvipcheckTc");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
