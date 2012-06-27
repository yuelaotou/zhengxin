package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CashDayClearReportForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    resetToken(request);   
    request.getSession().setAttribute("credenceType_gjp", "0");
    request.getSession().removeAttribute(CashDayClearReportShowAC.PAGINATION_KEY);
    return mapping.findForward("cashdayclearreport_show");
  }
}
