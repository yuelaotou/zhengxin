package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class CollFnComparisonEmpInfoMaintainAC extends LookupDispatchAction {

  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.empaccount", "queryEmpAccount");
    map.put("button.print", "print");
    map.put("button.back", "back");
    return map;
  }
  
  public ActionForward queryEmpAccount(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      // 得到职工信息
      String str = request.getParameter("id");
      String[] strArray = str.split(",");
      String empId = strArray[0];
      String empName = strArray[1];
      request.getSession().setAttribute("EmpAccount_empId", empId);
      request.getSession().setAttribute("EmpAccount_empName", empName);
      request.getSession().removeAttribute(CollFnComparisonEmpAccountShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonEmpAccountShowAC");
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    try {
      //    得到单位编号与单位名称
      String orgId = (String) request.getSession().getAttribute(
          "CollFnComparison_orgId");
      String orgName = (String) request.getSession().getAttribute(
          "CollFnComparison_orgName");
      List list = (List) request.getSession().getAttribute("empinfo_print");
      request.setAttribute("printlist", list);
      request.setAttribute("orgId", orgId);
      request.setAttribute("orgName", orgName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collfncomparisonempinfo_print");
  }
  
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    try {
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonShowAC");
  }
}
