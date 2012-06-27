package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.EmpOperationFlowAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class EmpOperationFlowTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    EmpOperationFlowAF f = (EmpOperationFlowAF) form;
    EmpOperationFlowAF empOperationFlowAF=new EmpOperationFlowAF();    
    request.setAttribute("empOperationFlowAF", empOperationFlowAF); 
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "empHAFAccountFlow.id", "DESC", criterions);
    String paginationKey = EmpOperationFlowTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }
  protected String getForword() {
    return "empOperationFlowTaShowAC";
  }
  protected HashMap makeCriterionsMap(EmpOperationFlowAF form) {
    HashMap m = new HashMap();
    String empid = form.getEmpid();
    if (!(empid == null || "".equals(empid))) {
      try {
        int iid = Integer.parseInt(empid);
        m.put("empid", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("empid", new Integer(-10000));
      }
    }
    String empname = form.getEmpname();
    if (!(empname == null || empname.length() == 0)){
      m.put("empname", form.getEmpname());
    }
    
    return m;
  }

}
