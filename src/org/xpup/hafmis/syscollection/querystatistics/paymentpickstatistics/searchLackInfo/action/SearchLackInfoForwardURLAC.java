package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchLackInfoForwardURLAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    session.setAttribute("searchLackInfoListAF", null);
    session.setAttribute("searchLackInfoHeadDTO", null);
    session.setAttribute("searchLackInfoContentDTO", null);    
//    session.setAttribute("cellList", null);
    session.setAttribute("firstin", "1");//标识是否是首次进入查询页面
    
    return mapping.findForward("to_searchLackInfo_list");
  }
}
