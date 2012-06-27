package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IssuenoticeTdForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.getSession().removeAttribute(IssuenoticeTdShowAC.PAGINATION_KEY);
    request.setAttribute("forward", "1");
    return mapping.findForward("issuenoticetd_show");
  }
}
