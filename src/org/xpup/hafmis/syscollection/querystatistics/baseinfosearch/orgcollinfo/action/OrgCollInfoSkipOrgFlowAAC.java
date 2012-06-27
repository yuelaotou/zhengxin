package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;

/**
 * 用来跳转到单位流水的action
 * 
 * @author 付云峰
 */
public class OrgCollInfoSkipOrgFlowAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String orgid = request.getParameter("orgid");
      Pagination pagination1 = new Pagination();
      pagination1.getQueryCriterions().put("orgid",orgid);
      request.getSession(false).setAttribute("sessionLJ", pagination1);
      String text = "displayOrgFlow()";
      response.getWriter().write(text); 
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }

}
