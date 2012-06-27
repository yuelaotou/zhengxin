package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;

/**
 * @author 李鹏 2007-7-10
 */
public class ClearAccountTafindAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    ClearAccountShowAF clearAccountShowAF = (ClearAccountShowAF) form;
    String bank = clearAccountShowAF.getBank1();
    String status = clearAccountShowAF.getBis_Status1();
    String type = clearAccountShowAF.getBis_type1();
    String docNum = clearAccountShowAF.getDocNum();
    String operator = clearAccountShowAF.getOperator1();
    String orgId = clearAccountShowAF.getOrgId();
    String orgName = clearAccountShowAF.getOrgName();
    String noteNum = clearAccountShowAF.getNoteNum();
    pagination.getQueryCriterions().put("bank", bank);
    pagination.getQueryCriterions().put("status", status);
    pagination.getQueryCriterions().put("type", type);
    pagination.getQueryCriterions().put("docNum", docNum);
    pagination.getQueryCriterions().put("operator", operator);
    pagination.getQueryCriterions().put("orgId", orgId);
    pagination.getQueryCriterions().put("orgName", orgName);
    pagination.getQueryCriterions().put("noteNum", noteNum);

    request.getSession(false).setAttribute("findaclearaccount_type", "1");// 判断是否有瞒足条件-->为1可以到101查询

    return mapping.findForward("showClearAccountAC");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgHAFAccountFlow.bizStatus",
          "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
