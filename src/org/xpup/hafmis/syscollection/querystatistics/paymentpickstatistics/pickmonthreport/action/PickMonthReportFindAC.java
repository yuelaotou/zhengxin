package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form.PickMonthReportFindAF;

public class PickMonthReportFindAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PickMonthReportFindAF af= (PickMonthReportFindAF)form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("pickMonthReportShowAC");
  }
  private String getPaginationKey() {
    return PickMonthReportShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PickMonthReportFindAF form) {
    HashMap m = new HashMap();
    
    String officeCode = form.getOfficeCode();//°ìÊÂ´¦
    if(officeCode!=null && !"".equals(officeCode)){
      m.put("office", officeCode.trim());
    }
    String bank = form.getCollBank();
    if(bank!=null && !"".equals(bank))
    {
      m.put("bank", bank.trim());
    }
    String startDate = form.getStartDate();
    if(startDate!=null && !"".equals(startDate)){
      m.put("startDate", startDate.trim());
    }
    String endDate = form.getEndDate();
    if(endDate!=null && !"".equals(endDate)){
      m.put("endDate", endDate.trim());
    }
    return m;
  }
}


