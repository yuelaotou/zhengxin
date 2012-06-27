package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
// import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.bsinterface.IEmployeeInfoListBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action.OrgBaseInfoShowAC;

public class EmpBaseInfoTaMaintainAC extends LookupDispatchAction {
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.printone", "printone");

    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        EmployeeInfoListShowAC.PAGINATION_KEY);
    IEmployeeInfoListBS pbs = (IEmployeeInfoListBS) BSUtils.getBusinessService(
        "employeeInfoListBS", this, mapping.getModuleConfig());
    List list = pbs.getEmployeeInfoAllList(pagination, securityInfo);
    request.setAttribute("printlist", list);
    return mapping.findForward("to_empBaseInfoListprint");
  }
  
  public ActionForward printone(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{

    String id=(String)request.getParameter("id");
    String iid=(String)request.getParameter("iid");
    IEmployeeInfoListBS pbs = (IEmployeeInfoListBS)BSUtils.getBusinessService("employeeInfoListBS",this,mapping.getModuleConfig());
    Integer orgId=new Integer(iid);
    Integer empId=new Integer(id);
    Emp emp=pbs.findEmpByOrdIdAndEmpId(orgId, empId);
    request.setAttribute("emp", emp);
    return mapping.findForward("to_empBaseInfoprint");
  }

}
