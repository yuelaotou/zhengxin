package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.OrgpaymentAF;
/**
 * 
 * @author ÓÚÇì·á
 *
 */
public class EmppaymentstatisticsPrintAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    OrgpaymentAF orgpaymentAF = new OrgpaymentAF();
    Pagination pagination = (Pagination)request.getSession().getAttribute(EmppaymentstatisticsShowAC.PAGINATION_KEY);
    orgpaymentAF.setEmpId(pagination.getQueryCriterions().get("empId")+"");
    orgpaymentAF.setEmpName(pagination.getQueryCriterions().get("empName")+"");
    orgpaymentAF.setPay_month(pagination.getQueryCriterions().get("payMonth")+"");
    request.setAttribute("orgpaymentAF", orgpaymentAF);
    return mapping.findForward("to_cell.jsp");
  }

}
