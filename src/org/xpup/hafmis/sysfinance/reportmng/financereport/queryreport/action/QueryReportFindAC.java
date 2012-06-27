package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public class QueryReportFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    QueryReportAF f = (QueryReportAF) form;

    HashMap criterions = makeCriterionsMap(f);
    
    String bizyear=request.getParameter("bizyear");
    String starperiod=request.getParameter("starperiod");
    String endperiod=request.getParameter("endperiod");  
    String office=request.getParameter("office");  

    if (!(bizyear == null || "".equals(bizyear))) {
      criterions.put("bizyear", bizyear);
    }
    
    if (!(starperiod == null || starperiod.length() == 0))
      if(starperiod.length()==1){
        starperiod="0"+starperiod;
      }
      criterions.put("starperiod", starperiod);
    
    if (!(endperiod == null || endperiod.length() == 0))
      if(endperiod.length()==1){
        endperiod="0"+endperiod;
      }
      criterions.put("endperiod", endperiod);
    
    if (!(office == null || office.length() == 0))
      criterions.put("office", office);

  
    Pagination pagination = new Pagination(0, 10, 1,
        "", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "queryReportShowAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.action.QueryReportShowAC";
  }

  protected HashMap makeCriterionsMap(QueryReportAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



