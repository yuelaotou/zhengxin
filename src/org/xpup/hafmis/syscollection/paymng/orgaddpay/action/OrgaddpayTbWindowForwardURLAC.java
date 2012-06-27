package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OrgaddpayTbWindowForwardURLAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbWindowShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String paymentid = request.getParameter("paymentid");
    request.setAttribute("paymentid", paymentid);
    request.getSession().setAttribute(PAGINATION_KEY, null);
    String type_month_pay=(String)request.getParameter("type");
    request.getSession().setAttribute("type_month_pay", type_month_pay);
    
    return mapping.findForward("show_orgaddpay");
  }

}