package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChppersonEmpForwardURLAC extends Action{
  
  public static final String PAGINATION_KEY2 = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonEmpListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY2, null);
    request.setAttribute("chgpersonEmpListAF", null);
    request.setAttribute("chgpersonEmpHead", null);
    session.setAttribute("firstinEmp", "1");//标识是否是首次进入查询页面
    
    return mapping.findForward("to_chgpersonemp_list");
  }
}
