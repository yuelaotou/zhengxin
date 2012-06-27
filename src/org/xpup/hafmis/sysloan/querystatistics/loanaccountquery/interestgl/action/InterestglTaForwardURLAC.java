package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * @author yuqf
 *
 */
public class InterestglTaForwardURLAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action.InterestglTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(PAGINATION_KEY, null);
    request.setAttribute("theinterestglTaAF", null);
    return mapping.findForward("to_interestglTaShowAC");
  }

}
