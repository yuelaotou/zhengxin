package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.form.PaymentYearReportAF;

public class PaymentYearReportFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PaymentYearReportAF af = (PaymentYearReportAF) form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("paymentYearReportShowAC");
  }

  private String getPaginationKey() {
    return PaymentYearReportShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PaymentYearReportAF form) {
    HashMap m = new HashMap();

    String officeCode = form.getOfficeCode();// °ìÊÂ´¦
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("office", officeCode.trim());
    }
    String bank = form.getCollBank();
    if (bank != null && !"".equals(bank)) {
      m.put("bank", bank.trim());
    }
    String year = form.getYear();
    if (year != null && !"".equals(year)) {
      m.put("year", year.trim());
    }
    return m;
  }
}
