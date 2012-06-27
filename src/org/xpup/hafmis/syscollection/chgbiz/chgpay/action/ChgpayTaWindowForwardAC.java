package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;

public class ChgpayTaWindowForwardAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaWindowForwardAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try {
      String id =request.getParameter("id");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      if (request.getParameter("id")!=null) {
        pagination.getQueryCriterions().put("pk_id", id);
      }
      PaginationUtils.updatePagination(pagination, request);
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS",
          this, mapping.getModuleConfig());
      ChgpayListAF chgpayListAF = chgpayBS.findChgpayWindowList(pagination);
      request.setAttribute("chgpayListAF", chgpayListAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_chgpay_window");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPaymentTail.empId", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
