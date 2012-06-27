package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ForwardAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute(ShowListAC.PAGINATION_KEY);
    request.getSession().removeAttribute("infoList");
    return mapping.findForward("showlist");
  }
}
