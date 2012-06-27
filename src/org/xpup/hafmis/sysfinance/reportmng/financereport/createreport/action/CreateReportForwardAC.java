package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author ЭѕСт
 * 2007-10-16
 */
public class CreateReportForwardAC extends Action {
  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action.CreateReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    session.setAttribute("savemethod", null);
    
    return mapping.findForward("createReportShowAC");
  }
}