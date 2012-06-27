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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

/**
 * @author 王野 2007-10-10
 */
public class EndorsecontractTdShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action.EndorsecontractTdShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    Pagination pagination = null;
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      pagination = getPagination(PAGINATION_KEY, request);
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      String pl123IdWY = request.getParameter("pl123IdWY");// pl121 ID
      // 当双击列表时，获取pl123主键ID，进行查询
      if (pl123IdWY != null && !pl123IdWY.equals("")) {
        endorsecontractTdAF = endorsecontractBS.updateAssurer(pl123IdWY,
            pagination, securityInfo, request);
      } else {
        // 默认进入列表时，显示列表中ID最大的记录
        String contractId = (String) request.getSession().getAttribute(
            "contractIdWY");
        endorsecontractTdAF = endorsecontractBS.queryAssurerList_(
            contractId, pagination, securityInfo, request);
        List list = endorsecontractTdAF.getList();
        request.getSession().setAttribute("loanvipcheckTdList", list);
      }
      List loanvipcheckTdList = (List) request.getSession().getAttribute(
          "loanvipcheckTdList");
      if (loanvipcheckTdList != null && !loanvipcheckTdList.equals("")) {
        endorsecontractTdAF.setList(loanvipcheckTdList);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("theEndorsecontractTdAF", endorsecontractTdAF);
    return mapping.findForward("to_loanvipcheckTd");
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
