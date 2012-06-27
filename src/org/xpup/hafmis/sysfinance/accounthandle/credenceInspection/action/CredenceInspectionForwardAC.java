package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CredenceInspectionForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);
    request.getSession().removeAttribute(
        CredenceInspectionShowAC.PAGINATION_KEY);
    request.setAttribute("type", "1");
    return mapping.findForward("to_credenceInspection_show");
  }
}
