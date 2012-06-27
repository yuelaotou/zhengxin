package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.bsinterface.IOverdueQueryBS;

/**
 * @author wangshuang 2009-04-03
 */
public class OverdueQueryTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action.OverdueQueryTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String contractId = request.getParameter("contractId");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      if (contractId != null) {
        pagination.getQueryCriterions().put("contractId", contractId);
      } else
        PaginationUtils.updatePagination(pagination, request);
      IOverdueQueryBS overdueQueryBS = (IOverdueQueryBS) BSUtils
          .getBusinessService("overdueQueryBS", this, mapping.getModuleConfig());

      list = overdueQueryBS.queryOverdueInfByContractid(pagination,
          securityInfo);
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_overduequerytb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "p111.contract_id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
