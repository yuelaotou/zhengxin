package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;

public class BadDebtDestroyTaFindAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {  
          String loanKouAcc=(String)request.getParameter("loanKouAcc");
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("loanKouAcc", loanKouAcc);
          pagination.getQueryCriterions().put("callbackMonth",null);
          text = "display('"+loanKouAcc+"');";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null; 
}

  protected String getPaginationKey() {
    return BadDebtDestroyTaShowAC.PAGINATION_KEY;
 }
}