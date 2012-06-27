package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoanaccordTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanaccord.loanaccord.action.LoanaccordTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      LoanaccordShowAF loanaccordShowAF = new LoanaccordShowAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
          "loanaccordBS", this, mapping.getModuleConfig());
      loanaccordShowAF = loanaccordBS.queryLoanaccordList(pagination,
          securityInfo);
      List printList = loanaccordShowAF.getPrintList();
      request.getSession().setAttribute("loanaccordPrintList", printList);
      // 插入下拉状态
      Map statusMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
      Map statusNewMap = new HashMap();
      statusNewMap.put(new Integer(BusiConst.BUSINESSSTATE_SURE), statusMap
          .get(new Integer(BusiConst.BUSINESSSTATE_SURE)));
      statusNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CHECK), statusMap
          .get(new Integer(BusiConst.BUSINESSSTATE_CHECK)));
      statusNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT),
          statusMap.get(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)));
      loanaccordShowAF.setStatusNewMap(statusNewMap);
      // 插入下拉列表银行
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if (!temp_banklist.isEmpty()) {
        Iterator itr = temp_banklist.iterator();
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank = (Userslogincollbank) itr
              .next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(),
              userslogincollbank.getCollBankId().toString()));
        }
      }
      request.getSession(true).setAttribute("banklist", banklist);
      request.setAttribute("loanaccordShowAF", loanaccordShowAF);
      loanaccordShowAF.reset(mapping, request);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanaccord_tb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "borrower.contractId", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("bizSt", "4");
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
