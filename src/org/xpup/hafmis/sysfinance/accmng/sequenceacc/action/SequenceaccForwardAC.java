package org.xpup.hafmis.sysfinance.accmng.sequenceacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action.CredenceclearShowAC;

public class SequenceaccForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);
    request.getSession().setAttribute("type", null);
    request.getSession().setAttribute("type2", "1");
    request.setAttribute("type3","1");
    request.getSession().removeAttribute(CredenceclearShowAC.PAGINATION_KEY);
    return mapping.findForward("to_sequenceacc_show");
  }
}
