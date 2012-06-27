package org.xpup.hafmis.sysloan.loancallback.consultation.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class ConsultationTaFindAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {  
          String id=(String)request.getParameter("id");
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("contractId", id);
          pagination.getQueryCriterions().put("callbackMonth",null);
          text = "display('"+id+"');";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return ConsultationTaShowAC.PAGINATION_KEY;
 }
}