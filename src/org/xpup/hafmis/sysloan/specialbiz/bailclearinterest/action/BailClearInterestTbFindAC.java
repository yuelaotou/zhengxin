package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author ÍõÒ° 2007-10-08
 */
public class BailClearInterestTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      BailClearInterestTbAF bailClearInterestTbAF = (BailClearInterestTbAF) form;
      HashMap criterions = makeCriterionsMap(bailClearInterestTbAF);
      Pagination pagination = new Pagination(0, 10, 1, "p202.biz_date, p202.loan_bank_id",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bailClearInterestTbShowAC");
  }

  protected HashMap makeCriterionsMap(BailClearInterestTbAF form) {
    HashMap m = new HashMap();

    String loanKouAcc = form.getLoanKouAcc().trim();
    if (loanKouAcc != null && loanKouAcc.length() > 0) {
      m.put("loanKouAcc", loanKouAcc);
    }

    String borrowerName = form.getBorrowerName().trim();
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }

    String bizYear = form.getBizYear().trim();
    if (bizYear != null && bizYear.length() > 0) {
      m.put("bizYear", bizYear);
    }

    String loanBankName = form.getLoanBankName().trim();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }
    m.put("findType", "find");
    return m;
  }

  protected String getPaginationKey() {
    return BailClearInterestTbShowAC.PAGINATION_KEY;
  }
}
