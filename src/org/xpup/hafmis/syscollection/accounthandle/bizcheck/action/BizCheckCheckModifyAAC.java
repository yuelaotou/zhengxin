package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BizCheckCheckModifyAAC extends Action{
  
                                              
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          
      
          String moneyType="";
          moneyType=(String)request.getParameter("moneyType");
          
          request.getSession().setAttribute("moneyType", moneyType);
         
          String text = null;
          text = "displays3('" + moneyType + "')";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}