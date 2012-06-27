package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InterestTbForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(InterestTbShowAC.PAGINATION_KEY, null);
    return mapping.findForward("interestTbShowAC");
  }
}
