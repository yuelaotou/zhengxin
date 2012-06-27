package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author ั๎นโ
 * 20090303
 */
public class LoanBackByFundForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(LoanBackByFundShowAC.PAGINATION_KEY, null);
      request.setAttribute("nosearch", "nosearch");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBackByFundShowAC");
  }
}
