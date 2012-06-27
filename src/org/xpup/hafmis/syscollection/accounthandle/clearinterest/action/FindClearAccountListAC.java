package org.xpup.hafmis.syscollection.accounthandle.clearinterest.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.form.ClearaccountShowAF;

public class FindClearAccountListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ClearaccountShowAF f = (ClearaccountShowAF) form;

    HashMap criterions = makeCriterionsMap(f);
    
    String officecode=request.getParameter("officecode");
    String bankcode=request.getParameter("bankcode");
    String orgcode=request.getParameter("orgcode");
    String orgname=request.getParameter("orgname");
    String oper=request.getParameter("oper");   
    
    if (!(officecode == null || "".equals(officecode))) {
      criterions.put("officecode", officecode);
    }
    
    if (!(bankcode == null || bankcode.length() == 0))
      criterions.put("bankcode", bankcode);
    
    
    if (!(orgcode == null || "".equals(orgcode))) {
      
      
      String org="";
      if(orgcode.length()==10){
        org=orgcode.substring(1, orgcode.length());
        criterions.put("orgcode", org);
      }else{
        criterions.put("orgcode", orgcode);
      }
  }
    
    if (!(orgname == null || orgname.length() == 0))
      criterions.put("orgname", orgname);
    
    if (!(oper == null || oper.length() == 0))
      criterions.put("oper", oper);

  
    Pagination pagination = new Pagination(0, 10, 1,
        "org.id", "ASC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_clearaccount_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accounthandle.clearinterest.action.ShowClearAccountListAC";
  }

  protected HashMap makeCriterionsMap(ClearaccountShowAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
