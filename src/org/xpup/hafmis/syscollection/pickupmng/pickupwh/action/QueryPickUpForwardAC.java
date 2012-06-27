package org.xpup.hafmis.syscollection.pickupmng.pickupwh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QueryPickUpForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("info");
    request.getSession().removeAttribute("orgList");
    request.getSession().setAttribute(PickupWHShowAC.VINDICATE, null);
    request.getSession().setAttribute("queryOrCheckPickUpWH", "query");
    return new ActionForward("/pickupWHShowAC.do");
  }
}
