package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonaddpayTbPickupdataWindowAF;

public class PersonaddpayTbPickupdataWindowSureAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      PersonaddpayTbPickupdataWindowAF personaddpayTbPickupdataWindowAF=(PersonaddpayTbPickupdataWindowAF)form;
      String addpayDateSt=(String)request.getParameter("addpayDateSt");
      String addpayDateEnd=(String)request.getParameter("addpayDateEnd");
      String paymentHeadId=(String)request.getParameter("paymentHeadId");
      String orgId=(String)request.getParameter("orgId");
      String noteNum=(String)request.getParameter("noteNum");
      String noteNum_1=(String)request.getParameter("noteNum_1");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      personAddPayBS.insertEmpAddPay(orgId, paymentHeadId,securityInfo,addpayDateSt,addpayDateEnd,noteNum,noteNum_1);
      request.setAttribute("orgId", orgId);
} catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_personaddpayta_show");
  }
}
