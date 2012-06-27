package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

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
public class EndorsecontractTaForwardURLAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(PAGINATION_KEY, null);
    request.getSession().setAttribute("contractId", null);
    request.getSession().setAttribute("comeFromType", null);
    request.getSession().setAttribute("pl121Id", null);
    request.setAttribute("theEndorsecontractTaAF", null);
    return mapping.findForward("to_EndorsecontractTaShowAC");
  }
}
