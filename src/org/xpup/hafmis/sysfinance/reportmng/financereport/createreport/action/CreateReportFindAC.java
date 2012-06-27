package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.form.CreateReportAF;

public class CreateReportFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CreateReportAF f = (CreateReportAF) form;

    HashMap criterions = makeCriterionsMap(f);
    
    String tablenamequery=request.getParameter("tablenamequery").trim();
    String createtimestart=request.getParameter("createtimestart").trim();
    String createtimeend=request.getParameter("createtimeend").trim();  

    if (!(tablenamequery == null || "".equals(tablenamequery))) {
      criterions.put("tablenamequery", tablenamequery);
    }
    
    if (!(createtimestart == null || createtimestart.length() == 0))
      criterions.put("createtimestart", createtimestart);
    
    if (!(createtimeend == null || createtimeend.length() == 0))
      criterions.put("createtimeend", createtimeend);

  
    Pagination pagination = new Pagination(0, 10, 1,
        "reportMng.id", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "createReportShowAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action.CreateReportShowAC";
  }

  protected HashMap makeCriterionsMap(CreateReportAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



