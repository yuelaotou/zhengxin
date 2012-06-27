package org.xpup.hafmis.sysloan.loanapply.loandelete.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * ´û¿î»§É¾³ý
 * @author yqf
 *20081015
 */
public class LoandeleteForwardURLAC  extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(LoandeleteShowAC.PAGINATION_KEY, null);
    return mapping.findForward("to_loandeleteShowAC");
  }
}
