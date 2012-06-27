package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChppersonOrgForwardURLAC extends Action {
  
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonOrgListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute("chgpersonOrgListAF", null);
    session.setAttribute("chgpersonOrgHeadDTO", null);
    session.setAttribute("cellList", null);
    session.setAttribute("firstin", "1");//标识是否是首次进入查询页面
    
    return mapping.findForward("to_chgpersonorg_list");
  }
}
