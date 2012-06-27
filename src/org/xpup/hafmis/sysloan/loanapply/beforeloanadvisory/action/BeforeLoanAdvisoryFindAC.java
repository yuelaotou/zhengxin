package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryFindAF;

public class BeforeLoanAdvisoryFindAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.empsearch", "empsearch");
    map.put("button.spousesearch", "spousesearch");
    return map;
    
  }
  
  public ActionForward empsearch(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    
    BeforeLoanAdvisoryFindAF af=(BeforeLoanAdvisoryFindAF)form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    request.getSession().setAttribute("type", "1");
    modifyPagination(pagination);
    af.reset();
    return mapping.findForward("to_beforeLoanAdvisoryShow");
    
  }
  public ActionForward spousesearch(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    
    BeforeLoanAdvisoryFindAF af=(BeforeLoanAdvisoryFindAF)form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    request.getSession().setAttribute("type", "2");
    modifyPagination(pagination);
    af.reset();
    return mapping.findForward("to_beforeLoanAdvisoryShow");
    
  }
  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action.BeforeLoanAdvisoryShowAC";
  }

  protected HashMap makeCriterionsMap(BeforeLoanAdvisoryFindAF form) {
    HashMap m = new HashMap();

    String empid =form.getEmpid();
    if (!(empid == null || "".equals(empid))) {
      m.put("empid", empid);
    }
    String empName = form.getEmpName();
    if (!(empName == null || "".equals(empName))) {
      m.put("empName", empName);
    }
    String empCardnum = form.getEmpCardNum();
    if (!(empCardnum == null || "".equals(empCardnum))) {
      m.put("empCardnum", empCardnum);
    }
    String orgid=form.getOrgid();
    if(!(orgid==null||"".equals(orgid)))
     {
      m.put("orgid", orgid);
      }
    String orgname = form.getOrgName();
    if (!(orgname == null || orgname.length() == 0)){
      m.put("orgname", orgname);
    }
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
