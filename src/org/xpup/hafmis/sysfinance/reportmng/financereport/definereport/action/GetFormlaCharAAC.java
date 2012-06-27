package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiTools;

public class GetFormlaCharAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {

      String key = request.getParameter("key");
      String value="";
      
      value=BusiTools.getExpressName(key);
      
      text="display_getvalue('"+value+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}