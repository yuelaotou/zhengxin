/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanVIPCheckForwardURLAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-25
 **/
package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OthersLoanTdForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(OthersLoanTbShowAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("type", "1");
    request.getSession().setAttribute("forward", "2");
    return mapping.findForward("othersLoanTbShowAC");
  }
}
