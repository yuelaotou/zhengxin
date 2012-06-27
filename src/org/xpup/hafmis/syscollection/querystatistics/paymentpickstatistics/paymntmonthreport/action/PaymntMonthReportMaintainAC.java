package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class PaymntMonthReportMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String operator = securityInfo.getUserInfo().getRealName();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PaymntMonthReportShowAC.PAGINATION_KEY);
    String year = (String) pagination.getQueryCriterions().get("year");
    String month = (String) pagination.getQueryCriterions().get("month");
    String yearMonth = year + (month.length() < 2 ? "0" + month : month);
    request.setAttribute("bizDate", yearMonth);
    request.setAttribute("operator", operator);
    return mapping.findForward("to_paymntMonthReport_cell");
  }
}
