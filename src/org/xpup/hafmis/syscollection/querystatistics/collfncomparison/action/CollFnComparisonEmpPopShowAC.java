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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpPopAF;

public class CollFnComparisonEmpPopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpPopShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CollFnComparisonEmpPopAF collFnComparisonEmpPopAF = new CollFnComparisonEmpPopAF();
    try {
      
      // 得到查询信息
      String orgId = (String) request.getSession().getAttribute("ComparisonEmpPop_orgId");
      String bizType = (String) request.getSession().getAttribute("ComparisonEmpPop_bizType");
      String date = (String) request.getSession().getAttribute("ComparisonEmpPop_date");
      String docNum = (String) request.getSession().getAttribute("ComparisonEmpPop_docNum");
      String orgName = (String) request.getSession().getAttribute("ComparisonEmpPop_orgName");
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey,request);
      pagination.getQueryCriterions().put("orgId", orgId);
      pagination.getQueryCriterions().put("docNum", docNum);
      PaginationUtils.updatePagination(pagination, request);
      
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      
      List list = collFnComparisonBS.queryEmpInfoPop(pagination, securityInfo);
      
      collFnComparisonEmpPopAF.setOrgId(orgId);
      collFnComparisonEmpPopAF.setBizType(bizType);
      collFnComparisonEmpPopAF.setDate(date);
      collFnComparisonEmpPopAF.setDocNum(docNum);
      collFnComparisonEmpPopAF.setOrgName(orgName);
      
      collFnComparisonEmpPopAF.setList(list);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("collFnComparisonEmpPopAF", collFnComparisonEmpPopAF);
    return mapping.findForward("to_collfncomparisonemppop_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a102.emp_id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
