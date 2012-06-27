package org.xpup.hafmis.sysfinance.accmng.totleacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TotleaccForwardAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);
    HttpSession session = request.getSession();
    session.setAttribute(TotleaccShowAC.PAGINATION_KEY, null);
    
    return mapping.findForward("to_totleacc_show");
  }
}