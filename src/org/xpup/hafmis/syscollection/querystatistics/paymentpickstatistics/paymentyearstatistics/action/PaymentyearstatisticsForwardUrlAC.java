package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 统计查询 -- 缴存提取统计 -- 公积金缴存情况年报表
 * @author yqf
 *  20080920
 *
 */
public class PaymentyearstatisticsForwardUrlAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action.PaymentyearstatisticsShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(PAGINATION_KEY, null);
    return mapping.findForward("to_paymentyearstatisticsShowAC");
  }
}
