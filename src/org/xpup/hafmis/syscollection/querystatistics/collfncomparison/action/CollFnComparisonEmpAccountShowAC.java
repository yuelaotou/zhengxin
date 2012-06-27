package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpAccountAF;

public class CollFnComparisonEmpAccountShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpAccountShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CollFnComparisonEmpAccountAF collFnComparisonEmpAccountAF = new CollFnComparisonEmpAccountAF();
    try {
      String empId = null;
      String empName = null;
      List list = new ArrayList();
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      
      // 得到单位信息
      String orgId = (String) request.getSession().getAttribute(
          "CollFnComparison_orgId");
      String orgName = (String) request.getSession().getAttribute(
          "CollFnComparison_orgName");
      // 得到职工编号与职工姓名

      empId = request.getParameter("emp_Id");
      empName = request.getParameter("emp_Name");
      if (empId != null && empName != null) {
        request.getSession().removeAttribute(
            CollFnComparisonEmpAccountShowAC.PAGINATION_KEY);
        request.getSession().setAttribute("EmpAccount_empId", empId);
        request.getSession().setAttribute("EmpAccount_empName", empName);
      }else{
       empId = (String) request.getSession().getAttribute("EmpAccount_empId");
       empName = (String)request.getSession().getAttribute("EmpAccount_empName");
       }
//      if (request.getAttribute("find_orgId") != null) {
//        orgId = (String) request.getAttribute("find_orgId");
//      }
//      if (request.getAttribute("find_orgName") != null) {
//        orgName = (String) request.getAttribute("find_orgName");
//      }
//      if (request.getAttribute("find_empId") != null) {
//        empId = (String) request.getAttribute("find_empId");
//      }
//      if (request.getAttribute("find_empName") != null) {
//        empName = (String) request.getAttribute("find_empName");
//      }
//      if (request.getAttribute("find_cardNum") != null) {
//        collFnComparisonEmpAccountAF.setCardNum((String) request
//            .getAttribute("find_cardNum"));
//      }
//      if (request.getAttribute("find_timeSt") != null) {
//        collFnComparisonEmpAccountAF.setTimeSt((String) request
//            .getAttribute("find_timeSt"));
//      }
//      if (request.getAttribute("find_timeEnd") != null) {
//        collFnComparisonEmpAccountAF.setTimeEnd((String) request
//            .getAttribute("find_timeEnd"));
//      }

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      // 如果第一次进入，将查询余额的列表
      if (request.getParameter("emp_Id") != null && request.getParameter("emp_Name") != null) {
        pagination.getQueryCriterions().put("orgId", orgId);
        pagination.getQueryCriterions().put("empId", empId);
        list = collFnComparisonBS.queryqcye(pagination, securityInfo);
        pagination.getQueryCriterions().put("qcyelist", list);
      }
//      if(request.getAttribute("qcyelist")!=null){
//        list = (List) request.getAttribute("qcyelist");
//        pagination.getQueryCriterions().put("qcyelist", list);
//      }

      Object[] obj = collFnComparisonBS.findCollFnComparisonEmpAccount(
          pagination, securityInfo);

      collFnComparisonEmpAccountAF.setEmpId(empId);
      collFnComparisonEmpAccountAF.setEmpName(empName);
      collFnComparisonEmpAccountAF.setOrgId(orgId);
      collFnComparisonEmpAccountAF.setOrgName(orgName);

      collFnComparisonEmpAccountAF.setList((List) obj[0]);
      request.getSession().setAttribute("empAccount_print", obj[1]);
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("collFnComparisonEmpAccountAF",
        collFnComparisonEmpAccountAF);
    return mapping.findForward("to_collfncomparisonempaccount_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a1.org_id,a1.emp_id,a1.sett_date,a1.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
