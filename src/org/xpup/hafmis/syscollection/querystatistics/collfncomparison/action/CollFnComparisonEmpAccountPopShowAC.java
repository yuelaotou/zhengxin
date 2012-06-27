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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpAccountPopAF;
/**
 * 职工账查询时用到的弹出框
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-10 下午03:14:16
 */
public class CollFnComparisonEmpAccountPopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpAccountPopShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CollFnComparisonEmpAccountPopAF collFnComparisonEmpAccountPopAF = new CollFnComparisonEmpAccountPopAF();

    try {
      String orgid = request.getParameter("orgid");
      String orgname = request.getParameter("orgname");
      String empid = request.getParameter("empid");
      String empname = request.getParameter("empname");
      String indexs = request.getParameter("indexs");
      if (orgid != null && orgname != null) {
        request.getSession().removeAttribute(PAGINATION_KEY);
        request.getSession().setAttribute(
            "CollFnComparisonEmpAccountPopShowAC_orgid", orgid);
        request.getSession().setAttribute(
            "CollFnComparisonEmpAccountPopShowAC_orgname", orgname);
        request.getSession().setAttribute(
            "CollFnComparisonEmpAccountPopShowAC_empid", empid);
        request.getSession().setAttribute(
            "CollFnComparisonEmpAccountPopShowAC_empname", empname);
        request.getSession().setAttribute(
            "CollFnComparisonEmpAccountPopShowAC_indexs", indexs);
      } else {
        orgid = (String) request.getSession().getAttribute(
            "CollFnComparisonEmpAccountPopShowAC_orgid");
        orgname = (String) request.getSession().getAttribute(
            "CollFnComparisonEmpAccountPopShowAC_orgname");
        empid = (String) request.getSession().getAttribute(
            "CollFnComparisonEmpAccountPopShowAC_empid");
        empname = (String) request.getSession().getAttribute(
            "CollFnComparisonEmpAccountPopShowAC_empname");
      }

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      pagination.getQueryCriterions().put("orgid", orgid);
      pagination.getQueryCriterions().put("orgname", orgname);
      pagination.getQueryCriterions().put("empid", empid);
      pagination.getQueryCriterions().put("empname", empname);

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List list = collFnComparisonBS.findEmpPopList(pagination, securityInfo);
      collFnComparisonEmpAccountPopAF.setList(list);
      request.setAttribute("collFnComparisonEmpAccountPopAF",
          collFnComparisonEmpAccountPopAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collfncomparisonempaccountpop_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a001.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
