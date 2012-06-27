package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdjustaccountForwardURLAC extends Action{
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {

    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute("findadjustWrongAccountHead_type", null);
    session.setAttribute("temp_adjustWrongAccountHead_id",null);
    session.setAttribute("empHAFAccountFlowOrgId", null);
    return mapping.findForward("to_adjustaccount_list");
    
  }

}
