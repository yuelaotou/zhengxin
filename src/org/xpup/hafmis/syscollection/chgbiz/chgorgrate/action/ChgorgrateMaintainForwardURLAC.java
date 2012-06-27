package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChgorgrateMaintainForwardURLAC extends Action{
  public static final String PAGINATION_KEY2 = "org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.ShowChgorgrateMaintainListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY2, null);
    session.setAttribute("firstsearch", "1");
    session.setAttribute("deleteflag", "1");
    session.setAttribute("newSumPay", null);
    
    return mapping.findForward("to_chgorgrateMaintain_list");
    
  }

}
