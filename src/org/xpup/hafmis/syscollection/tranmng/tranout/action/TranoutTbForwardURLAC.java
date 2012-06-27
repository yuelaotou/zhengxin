package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TranoutTbForwardURLAC extends Action {
  
    public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showFindTbAC";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
    throws Exception {
      
      HttpSession session = request.getSession();
      session.setAttribute(PAGINATION_KEY, null);
      session.setAttribute("org.id",null);
      session.setAttribute("type", null);
      return mapping.findForward("to_Tbshow");
 
    }
}
