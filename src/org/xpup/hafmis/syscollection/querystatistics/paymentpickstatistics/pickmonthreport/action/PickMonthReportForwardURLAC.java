package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.biz.emppop.bsinterface.IEmpPopBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface.IOrgpaymentstatisticsBS;

public class PickMonthReportForwardURLAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(PickMonthReportShowAC.PAGINATION_KEY, null);
    request.getSession().setAttribute(PickMonthReportBankPopShowAC.PAGINATION_KEY, null);
    return mapping.findForward("pickMonthReportShowAC");
  }
}


