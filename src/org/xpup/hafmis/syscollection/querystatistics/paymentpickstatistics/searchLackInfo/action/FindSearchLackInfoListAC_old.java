package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.form.SearchLackInfoListAF;

public class FindSearchLackInfoListAC_old extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SearchLackInfoListAF f = (SearchLackInfoListAF) form;

    HashMap m = makeCriterionsMap(f);

    String orgId_old_q = f.getOrgId_old_q(); 
    if(orgId_old_q != null && !"".equals(orgId_old_q)){
      m.put("orgId_old_q", orgId_old_q);
    }
    
    String orgName_old_q = f.getOrgName_old_q();
    if(orgName_old_q != null && !"".equals(orgName_old_q)){
      m.put("orgName_old_q", orgName_old_q);
    }
    
    String yearMonth_old_q = f.getYearMonth_old_q();
    if(yearMonth_old_q != null && !"".equals(yearMonth_old_q)){
      m.put("yearMonth_old_q", yearMonth_old_q);
    }
    
   
    Pagination pagination = new Pagination(0, 10, 1,
        "aa305.org_id", "DESC", m); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_searchLackInfo_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC_old";
  }

  protected HashMap makeCriterionsMap(SearchLackInfoListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
