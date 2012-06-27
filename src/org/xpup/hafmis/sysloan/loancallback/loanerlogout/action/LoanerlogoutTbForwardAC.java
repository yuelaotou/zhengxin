package org.xpup.hafmis.sysloan.loancallback.loanerlogout.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoanerlogoutTbForwardAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.getSession().removeAttribute(LoanerlogoutTbShowAC.PAGINATION_KEY);
    return mapping.findForward("loanerlogouttb_show");
  }
}
