package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PersonaddpayTbWindowForwardAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonaddpayTbWindowShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String paymentid = request.getParameter("paymentid");
    // 付云峰修改：将paymentid的值传到打印页的jsp中做为请求参数传会。
    request.getSession().setAttribute("payment_id", paymentid);
    request.setAttribute("paymentid", paymentid);
    request.getSession().setAttribute(PAGINATION_KEY, null);
    String type_month_pay=(String)request.getParameter("type");
    request.getSession().setAttribute("type_month_pay", type_month_pay);
    return mapping.findForward("personaddpayTbShow");
  }
}