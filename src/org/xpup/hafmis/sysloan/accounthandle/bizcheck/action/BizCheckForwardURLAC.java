package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;





public class BizCheckForwardURLAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.bizcheck.action.BizCheckShowListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(BizCheckForwardURLAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bizCheckShowListAC");
  }
}
