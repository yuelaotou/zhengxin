/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 09-20-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class BankQueryForwardAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action.BankQueryShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.setAttribute("type", "1");
    request.getSession().removeAttribute(PAGINATION_KEY);
    return mapping.findForward("bankquery_show");
  }
}