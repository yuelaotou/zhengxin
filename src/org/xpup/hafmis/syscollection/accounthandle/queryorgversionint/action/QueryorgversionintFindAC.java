package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.form.QueryorgversionintAF;


public class QueryorgversionintFindAC extends Action{

  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    QueryorgversionintAF queryorgversionintAF =(QueryorgversionintAF)form;
    criterions = makeCriterionsMap(queryorgversionintAF);
    Pagination pagination = new Pagination(0, 10, 1, null, "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    queryorgversionintAF.reset(mapping, request);
    return mapping.findForward("to_queryorgversionintShowAC");
  }
  protected String getPaginationKey() {

    return QueryorgversionintShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(QueryorgversionintAF form) {
    HashMap m = new HashMap();
    String orgId = form.getOrgId().trim();
    if(orgId != null && !"".equals(orgId)){
      m.put("orgId", orgId);
    }
    
    String orgName = form.getOrgName().trim();
    if(orgName != null && !"".equals(orgName)){
      m.put("orgName", orgName);
    }
    
    String empId = form.getEmpId().trim();
    if (empId != null && !"".equals(empId)) {
      m.put("empId", empId.trim());
    }

    String empName = form.getEmpName().trim();
    if (empName != null && !"".equals(empName)) {
      m.put("empName", empName.trim());
    }

    String clearInterestType = form.getClearInterestType().trim();
    if (clearInterestType != null && !"".equals(clearInterestType)) {
      m.put("clearInterestType", clearInterestType.trim());
    }

    m.put("key", "value");
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
