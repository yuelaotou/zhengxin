package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.form.BankQueryAF;

public class BankQueryFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      BankQueryAF bankQueryAF = (BankQueryAF) form;
      HashMap criterions = makeCriterionsMap(bankQueryAF);
      Pagination pagination = new Pagination(0, 10, 1, "office,loanbankid,id ",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      bankQueryAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bankquery_show");
  }

  protected String getPaginationKey() {
    return BankQueryShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(BankQueryAF form) {
    HashMap m = new HashMap();
    String office = form.getOffice();
    if (!(office == null || office.length() == 0))
      m.put("office", form.getOffice().trim());
    String loanBankId = form.getLoanBankId().toString();
    if (!(loanBankId == null || loanBankId.length() == 0)) {
      m.put("loanBankId", form.getLoanBankId().toString().trim());
    }
    String bankPrisident = form.getBankPrisident();
    if (!(bankPrisident == null || bankPrisident.length() == 0)) {
      m.put("bankPrisident", form.getBankPrisident().trim());
    }
    String contactPrsn = form.getContactPrsn();
    if (!(contactPrsn == null || contactPrsn.length() == 0)) {
      m.put("contactPrsn", form.getContactPrsn().trim());
    }
    return m;
  }
}
