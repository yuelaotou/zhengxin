package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form.YearclearstatisticsListAF;

public class FindYearclearEmpListAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    YearclearstatisticsListAF f = (YearclearstatisticsListAF) form;

    HashMap m = makeCriterionsMap(f);

    String orgId = f.getOrgId();
    if(orgId != null && !"".equals(orgId.trim())){
      m.put("orgId", orgId.trim());
    }
    
    String orgName = f.getOrgName();
    if(orgName != null && !"".equals(orgName.trim())){
      m.put("orgName", orgName.trim());
    }
    String empId = f.getEmpId();
    if(empId != null && !"".equals(empId.trim())){
      m.put("empId", empId.trim());
    }
    
    String chgYearStart = f.getChgYearStart();
    if(chgYearStart != null && !"".equals(chgYearStart.trim())){
      m.put("chgYearStart", chgYearStart.trim());
    }
    
    String chgYearEnd = f.getChgYearEnd();
    if(chgYearEnd != null && !"".equals(chgYearEnd.trim())){
      m.put("chgYearEnd", chgYearEnd.trim());
    }
    String isZero = f.getIsZero();
    if(isZero != null && !"".equals(isZero)){
      m.put("isZero", isZero);
    }
    Pagination pagination = new Pagination(0, 10, 1,
        "aa001.id", "DESC", m); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_yearclearEmp_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearEmpListAC";
  }

  protected HashMap makeCriterionsMap(YearclearstatisticsListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
