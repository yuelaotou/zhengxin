package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

public class ChgpayEmpTaFindAAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaSouwAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String empid = request.getParameter("chgPaymentTail.empId");
      String text = null;  
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      ChgPaymentTail chgPaymentTail = chgpayBS.findEmpinfo(empid, orgid);
      text = "displays('" + chgPaymentTail.getEmp().getEmpInfo().getName()
          + "','" + chgPaymentTail.getEmp().getEmpInfo().getCardKind() + "','"
          + chgPaymentTail.getEmp().getEmpInfo().getCardNum() + "','"
          + chgPaymentTail.getEmp().getEmpInfo().getBirthday() + "','"
          + chgPaymentTail.getEmp().getEmpInfo().getSex() + "',"
          + chgPaymentTail.getEmp().getOrgPay() + ","
          + chgPaymentTail.getEmp().getEmpPay() + ","
          + chgPaymentTail.getEmp().getSalaryBase() + ")";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException e) {
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    }
    return null;
  }

  protected String getForword() {
    return "show_org_salarybase_changement_list";
  }

  protected String getPaginationKey() {
    return ChgpayTaSouwAC.PAGINATION_KEY;
  }
}
