package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PaymntPickYearForwardURLAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(PaymntPickTaShowAC.PAGINATION_KEY, null);
    request.setAttribute("url", "1");
    return mapping.findForward("paymntPickTaShowAC");
  }
}


