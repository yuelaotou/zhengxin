package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from.ChgpersonEmpListAF;

public class FindChgpersonEmpMaintainListAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ChgpersonEmpListAF f = (ChgpersonEmpListAF) form;

    HashMap m = makeCriterionsMap(f);
    
    String orgId = f.getOrgId();
    if(orgId != null && !"".equals(orgId)){
      m.put("orgId", orgId);
    }
    
    String orgName = f.getOrgName();
    if(orgName != null && !"".equals(orgName)){
      m.put("orgName", orgName);
    }
    
    String empId = f.getEmpId();
    if(empId != null && !"".equals(empId)){
      m.put("empId", empId);
    }
    
    String empName = f.getEmpName();
    if(empName != null && !"".equals(empName)){
      m.put("empName", empName);
    }
    
    String chgMonthStart = f.getChgMonthStart();
    if(chgMonthStart != null && !"".equals(chgMonthStart)){
      m.put("chgMonthStart", chgMonthStart);
    }
    
    String chgMonthEnd = f.getChgMonthEnd();
    if(chgMonthEnd != null && !"".equals(chgMonthEnd)){
      m.put("chgMonthEnd", chgMonthEnd);
    }
    
    String chgDateStart = f.getChgDateStart();
    if(chgDateStart != null && !"".equals(chgDateStart)){
      m.put("chgDateStart", chgDateStart);
    }
    
    String chgDateEnd = f.getChgDateEnd();
    if(chgDateEnd != null && !"".equals(chgDateEnd)){
      m.put("chgDateEnd", chgDateEnd);
    }
  
    Pagination pagination = new Pagination(0, 10, 1,
        "chgPersonTail.id", "DESC", m); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_chgpersonemp_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonEmpListAC";
  }

  protected HashMap makeCriterionsMap(ChgpersonEmpListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
