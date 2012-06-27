package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonaddpayTbPickupdataWindowAF;

public class PersonaddpayTbPickupdataWindowShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String paymentHeadId=(String)request.getParameter("paymentHeadId");
      String orgId=(String)request.getParameter("orgId");
      String noteNum=(String)request.getParameter("noteNum");
      PersonaddpayTbPickupdataWindowAF personaddpayTbPickupdataWindowAF=new PersonaddpayTbPickupdataWindowAF();
      if(!"".equals(paymentHeadId)){
        personaddpayTbPickupdataWindowAF.setPaymentHeadId(paymentHeadId);
      }
      if(!"".equals(orgId)){
        personaddpayTbPickupdataWindowAF.setOrgId(orgId);
      }
      if(!"".equals(noteNum)){
        personaddpayTbPickupdataWindowAF.setNoteNum(noteNum);
      }
      personaddpayTbPickupdataWindowAF.setAddpayDateEnd("");
      personaddpayTbPickupdataWindowAF.setAddpayDateSt("");
      request.setAttribute("personaddpayTbPickupdataWindowAF", personaddpayTbPickupdataWindowAF);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return mapping.findForward("to_personaddpay_pickupdatawindow_show");
  }
}
