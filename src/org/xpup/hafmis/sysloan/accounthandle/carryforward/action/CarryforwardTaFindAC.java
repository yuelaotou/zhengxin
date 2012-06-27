package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;

public class CarryforwardTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CarryforwardShowAF carryforwardShowAF = (CarryforwardShowAF) form;
    HashMap criterions = makeCriterionsMap(carryforwardShowAF);
    Pagination pagination = new Pagination(0, 10, 1, "restoreLoan.contractId", "DESC",
        criterions);

    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("carryforwardTaShowAC");
  }

  protected HashMap makeCriterionsMap(CarryforwardShowAF form) {
    HashMap m = new HashMap();

    String loanBankId = form.getLoanBankIdf().trim();// Æ¾Ö¤±àºÅ
    if (loanBankId != null && !"".equals(loanBankId)) {
      m.put("loanBankId", loanBankId);
    }
    return m;
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.accounthandle.carryforward.action.CarryforwardTaShowAC";
  }
}
