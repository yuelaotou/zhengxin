package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form.OverdueQueryTaAF;

public class OverdueQueryTaFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OverdueQueryTaAF overdueQueryTaAF = (OverdueQueryTaAF) form;
    HashMap criterions = makeCriterionsMap(overdueQueryTaAF);
    Pagination pagination = new Pagination(0, 10, 1,  "p111.contract_id", "ASC",
        criterions); 
    String paginationKey = OverdueQueryTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("overdueQueryTaShowAC");
  }
  protected HashMap makeCriterionsMap(OverdueQueryTaAF form) {
    HashMap m = new HashMap();
    String office = form.getOfficeCode();
    if (office != null && !"".equals(office.trim())) {
      m.put("office", office.trim());
    }
    String bankId = form.getBankId();
    if (bankId != null && !"".equals(bankId)) {
      m.put("bankId", bankId.trim());
    }
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId.trim())) {
      m.put("contractId", contractId.trim());
    }
    String borrowerName = form.getBorrowerName();
    if (borrowerName != null && !"".equals(borrowerName.trim())) {
      m.put("borrowerName", borrowerName.trim());
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && !"".equals(cardNum.trim())) {
      m.put("cardNum", cardNum.trim());
    }
    String loanKouAcc = form.getLoanKouAcc();
    if (loanKouAcc != null && !"".equals(loanKouAcc.trim())) {
      m.put("loanKouAcc", loanKouAcc.trim());
    }
    String overdueMonthSt = form.getOverdueMonthSt();
    if (overdueMonthSt != null && !"".equals(overdueMonthSt.trim())) {
      m.put("overdueMonthSt", overdueMonthSt.trim());
    }
    String overdueMonthEnd = form.getOverdueMonthEnd();
    if (overdueMonthEnd != null && !"".equals(overdueMonthEnd.trim())) {
      m.put("overdueMonthEnd", overdueMonthEnd.trim());
    }
    String agreement = form.getAgreement();
    if (agreement != null && !"".equals(agreement.trim())) {
      m.put("agreement", agreement.trim());
    }
    return m;
  }
}
