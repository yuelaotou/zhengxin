package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
 
public class BadDebtDestroyTbForwardURLWindowAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    String contractId = request.getParameter("contractId");
    String headId = request.getParameter("headId");
    request.setAttribute("headId", headId);
    request.setAttribute("contractId", contractId);
    request.getSession().setAttribute(BadDebtDestroyTbShowWindowAC.PAGINATION_KEY, null);
    return mapping.findForward("badDebtDestroyTbShowWindowAC");
  }
}
 