package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;

public class MonthpayTbWindowMXExportAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      Pagination pagination = (Pagination) request.getSession().getAttribute(
          MonthpayTbWindowMXShowAC.PAGINATION_KEY);
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());

      String empIds = request.getParameter("empIds");
      String empNames = request.getParameter("empNames");
      String orgPays = request.getParameter("orgPays");
      String statuss = request.getParameter("statuss");
      String tatol = request.getParameter("tatol");
      int finalTatol = (Integer.parseInt(tatol) - 1);
      String order[] = new String[finalTatol];

      if ((Integer.parseInt(empIds)) != 0) {
        order[(Integer.parseInt(empIds) - 1)] = "monthPaymentTail.empId";
      }
      if ((Integer.parseInt(empNames)) != 0) {
        order[(Integer.parseInt(empNames) - 1)] = "monthPaymentTail.empName";
      }
      if ((Integer.parseInt(orgPays)) != 0) {
        order[(Integer.parseInt(orgPays) - 1)] = "monthPaymentTail.orgShouldPay";
      }
      if ((Integer.parseInt(statuss)) != 0) {
        order[(Integer.parseInt(statuss) - 1)] = "emp.payStatus";
      }
      List expList = monthpayBS.findTaillistMXExport(pagination, order);

      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=orgaddpay_exp");
        return null;
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！"
          + bex.getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("monthpayTbWindowMXShowAC");
  }

}