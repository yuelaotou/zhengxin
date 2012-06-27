package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CredenceclearForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);
    request.getSession().setAttribute("type", null);
    request.getSession().removeAttribute("type2");
    request.getSession().removeAttribute(CredenceclearShowAC.PAGINATION_KEY);
    return mapping.findForward("to_credenceclear_show");
  }
}
