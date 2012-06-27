package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;

/**
 * @author ÍõÒ° 2007-10-05
 */
public class BailClearInterestTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      BailClearInterestTaAF bailClearInterestTaAF = (BailClearInterestTaAF) form;
      HashMap criterions = makeCriterionsMap(bailClearInterestTaAF);
      Pagination pagination = new Pagination(0, 10, 1, "p111.loan_bank_id",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bailClearInterestTaShowAC");
  }

  protected HashMap makeCriterionsMap(BailClearInterestTaAF form) {
    HashMap m = new HashMap();

    String loanBankName = form.getLoanBankName().trim();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }
    return m;
  }

  protected String getPaginationKey() {
    return BailClearInterestTaShowAC.PAGINATION_KEY;
  }
}
