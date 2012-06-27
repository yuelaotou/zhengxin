package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action;

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
public class AssureborrowerqueryForwardAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action.AssureborrowerqueryShowAC";
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
    request.setAttribute("type", "1");
		return mapping.findForward("assureborrower_show");
	}
}