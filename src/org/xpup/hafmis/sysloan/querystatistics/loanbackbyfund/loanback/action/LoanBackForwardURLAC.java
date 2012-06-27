package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author ั๎นโ 20090512
 */
public class LoanBackForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(LoanBackShowAC.PAGINATION_KEY, null);
      request.getSession(true).setAttribute("printList", null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBackShowAC");
  }
}
