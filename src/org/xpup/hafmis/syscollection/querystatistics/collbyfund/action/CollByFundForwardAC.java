package org.xpup.hafmis.syscollection.querystatistics.collbyfund.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CollByFundForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().setAttribute(CollByFundShowAC.PAGINATION_KEY, null);
    request.setAttribute("forward", "forward");
    return mapping.findForward("collByFundShowAC");
  }
}
