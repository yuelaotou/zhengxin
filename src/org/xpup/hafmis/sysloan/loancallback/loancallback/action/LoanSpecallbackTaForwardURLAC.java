package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
 
public class LoanSpecallbackTaForwardURLAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.getSession().setAttribute(LoancallbackTaShowAC.PAGINATION_KEY, null);
    request.getSession().setAttribute("param", null);
    request.setAttribute("param", "special");
    return mapping.findForward("loancallbackTaShowAC");
  }

}
 