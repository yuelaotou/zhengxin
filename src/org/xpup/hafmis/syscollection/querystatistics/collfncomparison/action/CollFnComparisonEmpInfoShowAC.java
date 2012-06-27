package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpInfoAF;

public class CollFnComparisonEmpInfoShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpInfoShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CollFnComparisonEmpInfoAF collFnComparisonEmpInfoAF = new CollFnComparisonEmpInfoAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // 得到单位编号与单位名称
      // String orgId = (String) request.getSession().getAttribute(
      // "CollFnComparison_orgId");
      // String orgName = (String) request.getSession().getAttribute(
      // "CollFnComparison_orgName");
      String orgId = request.getParameter("id");
      String orgName = request.getParameter("name");
      // 判断是否需要清Pagination
      if (orgId != null && orgName != null) {
        request.getSession().removeAttribute(
            CollFnComparisonEmpInfoShowAC.PAGINATION_KEY);
        request.getSession().setAttribute("CollFnComparison_orgId", orgId);
        request.getSession().setAttribute("CollFnComparison_orgName", orgName);
      } else {
        orgId = (String) request.getSession().getAttribute(
            "CollFnComparison_orgId");
        orgName = (String) request.getSession().getAttribute(
            "CollFnComparison_orgName");
      }
      collFnComparisonEmpInfoAF.setOrgId(orgId);
      collFnComparisonEmpInfoAF.setOrgName(orgName);
      // 如果是从Find进入则保留查询条件

      if (request.getAttribute("find_empid") != null) {
        collFnComparisonEmpInfoAF.setEmpId((String)request.getAttribute("find_empid"));
      }
      if (request.getAttribute("find_empname") != null) {
        collFnComparisonEmpInfoAF.setEmpName((String)request.getAttribute("find_empname"));
      }
      if (request.getAttribute("find_cardnum") != null) {
        collFnComparisonEmpInfoAF.setCardNum((String)request.getAttribute("find_cardnum"));
      }
      String paginationKey = this.getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("orgId", orgId);

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());

      Object[] obj = collFnComparisonBS.findCollFnComparisonEmpInfo(pagination,
          securityInfo);

      collFnComparisonEmpInfoAF.setList((List) obj[0]);
      // 将打印的list放入session
      request.getSession().setAttribute("empinfo_print", (List) obj[1]);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request
        .setAttribute("collFnComparisonEmpInfoAF", collFnComparisonEmpInfoAF);
    return mapping.findForward("to_collfncomparisonempinfo_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
