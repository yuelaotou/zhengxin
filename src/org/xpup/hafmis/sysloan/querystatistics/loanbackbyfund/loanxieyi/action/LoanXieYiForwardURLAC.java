package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Õı“∞ 2007-10-15
 */
public class LoanXieYiForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(LoanXieYiShowAC.PAGINATION_KEY, null);
      request.getSession(true).setAttribute("printLoanXieYiList", null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanXieYiShowAC");
  }
}
