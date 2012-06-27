package org.xpup.hafmis.syscollection.querystatistics.collbyfund.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class CollByFundMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.print.bank", "bank");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.setAttribute("userName", securityInfo.getRealName());
      request.setAttribute("bizDate", bizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collbyfund_cell");
  }

  public ActionForward bank(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      Pagination pagination = getPagination(CollByFundShowAC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      if (pagination.getQueryCriterions().get("batchNum") != null) {
        request.setAttribute("batchNum", pagination.getQueryCriterions().get(
            "batchNum").toString());
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String userName = securityInfo.getRealName();
      request.setAttribute("userName", userName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collbyfund_bankcell");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "a001.id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}