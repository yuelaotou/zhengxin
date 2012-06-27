/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanVIPCheckForwardURLAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-25
 **/
package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoanVIPCheckForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute("url", null);
      request.getSession().setAttribute(LoanVIPCheckShowAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanvipcheckShowAC");
  }
}
