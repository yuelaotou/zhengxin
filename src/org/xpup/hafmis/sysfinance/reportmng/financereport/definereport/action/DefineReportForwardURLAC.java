package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;

public class DefineReportForwardURLAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action.DefineReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    String tablename = request.getParameter("tablename");
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    pagination.getQueryCriterions().put("tablename", tablename);

    return mapping.findForward("defineReportShowAC");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}