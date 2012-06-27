package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CollFnComparisonOrgAccountForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      String orgid=(String)request.getParameter("id");
      String orgname=(String)request.getParameter("name");
      // 将单位编号与姓名放入session
      request.getSession().setAttribute("orgId_gjp", orgid);
      request.getSession().setAttribute("orgName_gjp", orgname);
      request.getSession().removeAttribute("collFnComparisonOrgAccountShowAF");
      
      request.setAttribute("type", "1");
      
      request.getSession().removeAttribute(CollFnComparisonOrgAccountShowAC.PAGINATION_KEY);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonOrgAccountShowAC");
  }
}
