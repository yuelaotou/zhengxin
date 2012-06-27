package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class YearclearstatisticsForwardURLAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearstatisticsListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    session.setAttribute("yearclearstatisticsListAF", null);
    session.setAttribute("yearclearstatisticsHeadDTO", null);
    session.setAttribute("firstin", "1");//标识是否是首次进入查询页面
    
    return mapping.findForward("to_yearclearstatistics_list");
  }
}