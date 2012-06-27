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
public class InterestglTbForwardURLAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.setAttribute("theinterestglTaAF", null);
    request.getSession().setAttribute(InterestglTbShowAC.PAGINATION_KEY, null);
    return mapping.findForward("to_interestglTbShowAC");
  }

}
