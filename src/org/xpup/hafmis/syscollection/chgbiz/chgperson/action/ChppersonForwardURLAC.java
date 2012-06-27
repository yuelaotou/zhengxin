package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChppersonForwardURLAC extends Action{
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {

    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute("orgID",null);
    session.setAttribute("orgIDByChgPersonHeadID", null);
    session.setAttribute("deleteflag", "1");
    
    
    return mapping.findForward("to_chgpersonDo_list");
    
  }

}
