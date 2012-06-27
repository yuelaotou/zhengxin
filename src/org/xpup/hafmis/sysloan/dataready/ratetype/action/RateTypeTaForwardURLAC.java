package org.xpup.hafmis.sysloan.dataready.ratetype.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RateTypeTaForwardURLAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    session.setAttribute(RateTypeTaShowAC.PAGINATION_KEY, null);
    return mapping.findForward("rateTypeTaShowAC");
  }
}
