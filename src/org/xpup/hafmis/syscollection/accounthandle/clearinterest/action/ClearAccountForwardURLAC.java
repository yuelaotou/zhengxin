package org.xpup.hafmis.syscollection.accounthandle.clearinterest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClearAccountForwardURLAC extends Action{
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.accounthandle.clearinterest.action.ShowClearAccountListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {

    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute("officeList1", null);
    session.setAttribute("bankList1", null);
    session.setAttribute("operList1", null);
    
    
    return mapping.findForward("to_clearaccount_list");
    
  }

}
