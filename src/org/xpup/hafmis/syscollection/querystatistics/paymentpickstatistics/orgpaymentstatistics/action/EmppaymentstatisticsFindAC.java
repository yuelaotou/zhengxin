package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.OrgpaymentAF;

public class EmppaymentstatisticsFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   
    OrgpaymentAF orgpaymentAF = (OrgpaymentAF)form;
      
    HashMap criterions = makeCriterionsMap(orgpaymentAF);
    Pagination  pagination = new Pagination(0, 10, 1, "null", "ASC", criterions);
    String paginationKey = getPaginationKey();

    request.getSession().setAttribute(paginationKey, pagination);
 
    return mapping.findForward("to_emppaymentstatisticsShowAC");
  }
  
  protected String getPaginationKey() {
    return EmppaymentstatisticsShowAC.PAGINATION_KEY;
  }

  
  protected HashMap makeCriterionsMap(OrgpaymentAF form) {
    HashMap m = new HashMap();
    
    String empId = form.getEmpId();
    if (empId != null && !"".equals(empId)) {
      m.put("empId", empId);
    }
    
    String empName = form.getEmpName();
    if(empName != null && !"".equals(empName)){
      m.put("empName", empName);
    }
    
    String pay_month = form.getPay_month();
    if (pay_month != null && pay_month.length() > 0) {
      m.put("pay_month", pay_month);
    }
 
    return m;  
  }

}
