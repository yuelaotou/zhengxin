package org.xpup.hafmis.syscollection.paymng.paysure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PaymentHeadForwardUrlAC extends Action{
  //public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.paysure.action.PaymentTaFindAC";
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.paysure.action.ShowPaymentHeadAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(PAGINATION_KEY, null);
    return mapping.findForward("to_PaymentHeadFindAC");
  }

}
