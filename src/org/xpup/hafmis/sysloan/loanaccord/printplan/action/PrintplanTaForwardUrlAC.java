package org.xpup.hafmis.sysloan.loanaccord.printplan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PrintplanTaForwardUrlAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanaccord.printplan.action.PrintplanTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    try{
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("printplanTaShowAC");
  }

}
