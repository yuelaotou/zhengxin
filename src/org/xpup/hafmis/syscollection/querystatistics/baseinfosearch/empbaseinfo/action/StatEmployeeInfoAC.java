package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.bsinterface.IEmployeeInfoListBS;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
public class StatEmployeeInfoAC extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    try{
//      System.out.println("经过统计->职工信息统计的Action");
      IEmployeeInfoListBS pbs = (IEmployeeInfoListBS)BSUtils.getBusinessService("employeeInfoListBS",this,mapping.getModuleConfig());
      String orgId = request.getParameter("orgId");
      String empId = request.getParameter("empId");
      Emp emp = pbs.findEmpByOrdIdAndEmpId(new Integer(orgId), new Integer(empId));
      request.setAttribute("employee", emp);
    }catch(Exception s){
      s.printStackTrace();
    }
		return new ActionForward("/stat_employee_info_list.jsp");
	}
}