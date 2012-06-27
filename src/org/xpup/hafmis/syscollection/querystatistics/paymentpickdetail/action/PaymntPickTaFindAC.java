package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form.PaymntPickAF;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.form.PickYearReportFindAF;

public class PaymntPickTaFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PaymntPickAF af = (PaymntPickAF) form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("paymntPickTaShowAC");
  }

  private String getPaginationKey() {
    return PaymntPickTaShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PaymntPickAF form) {
    HashMap m = new HashMap();

    String officeCode = form.getOffice();// °ìÊÂ´¦
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("office", officeCode.trim());
    }
    String year = form.getYear();
    if (year != null && !"".equals(year)) {
      m.put("year", year.trim());
    }
    String month = form.getMonth();
    if (year != null && !"".equals(year.trim())) {
      m.put("month", month);
    }
    return m;
  }
}
