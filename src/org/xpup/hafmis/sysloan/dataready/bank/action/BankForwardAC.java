package org.xpup.hafmis.sysloan.dataready.bank.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 09-20-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BankForwardAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.bank.action.ShowBankAC";
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
	  HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);  
		return mapping.findForward("showBankAC");
	}
}