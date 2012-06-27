package org.xpup.hafmis.sysloan.loanaccord.printplan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PrintplanPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    try{
      String url=(String)request.getAttribute("url");
      if(url!=null&&url.equals("")){
        request.setAttribute("URL", "url");
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_printplan_print");
  }

}
