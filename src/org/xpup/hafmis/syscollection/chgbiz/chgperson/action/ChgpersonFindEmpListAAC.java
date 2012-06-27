package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;

public class ChgpersonFindEmpListAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String id = request.getParameter("orgID");
      String text = null;
      
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      if (!(id == null || "".equals(id))) {
        pagination.getQueryCriterions().put("id", id);
      }else{
        pagination.getQueryCriterions().put("id", "1");
      }
      text="display_automess()";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
    }
    
    return null; 
} 
  protected String getPaginationKey() {
    return ShowChgpersonDoListAC.PAGINATION_KEY;
 }
}
