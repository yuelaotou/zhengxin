package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 统计查询 -- 缴存提取统计 -- 公积金缴存情况年报表
 * 
 * @author yg 2009-04-20
 */
public class PaymentYearReportForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(PaymentYearReportShowAC.PAGINATION_KEY, null);
    return mapping.findForward("paymentYearReportShowAC");
  }
}
