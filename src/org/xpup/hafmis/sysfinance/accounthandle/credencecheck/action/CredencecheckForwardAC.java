package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CredencecheckForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);
    request.getSession().setAttribute("type", null);
    request.getSession().removeAttribute(CredencecheckShowAC.PAGINATION_KEY);
    return mapping.findForward("to_credencecheck_show");
  }
}
