package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClearAccountsForwardURLAC extends Action{
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {

    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute("clearAccountShowAF", null);
    session.setAttribute("findaclearaccount_type", null);
    
    return mapping.findForward("to_clearaccount_list");
    
  }

}
