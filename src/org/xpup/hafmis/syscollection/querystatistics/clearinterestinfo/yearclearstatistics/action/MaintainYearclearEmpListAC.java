package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;

public class MaintainYearclearEmpListAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearEmpListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);

    List listcount = (List) pagination.getQueryCriterions().get("listcount_yearclear");
    HttpSession session = request.getSession();
    session.setAttribute("listcount_yearclear", listcount);

    return mapping.findForward("to_yearclearEmpList_report");
  }   

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    return mapping.findForward("to_yearclearEmpList_back");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa001.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
