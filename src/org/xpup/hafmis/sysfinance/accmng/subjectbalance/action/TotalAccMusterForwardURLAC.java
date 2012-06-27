package org.xpup.hafmis.sysfinance.accmng.subjectbalance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TotalAccMusterForwardURLAC 
extends Action {
  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysfinance.accmng.subjectbalance.action.SubjectbalanceShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    session.setAttribute("url", "1");
    resetToken(request);
    
    return mapping.findForward("subjectbalanceShowAC");
  }
}