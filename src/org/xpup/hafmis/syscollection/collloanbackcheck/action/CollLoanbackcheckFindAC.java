package org.xpup.hafmis.syscollection.collloanbackcheck.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.collloanbackcheck.form.CollLoanbackcheckAF;

public class CollLoanbackcheckFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CollLoanbackcheckAF f = (CollLoanbackcheckAF) form;

    HashMap criterions = makeCriterionsMap(f);
    
    String officeCode=request.getParameter("officeCode");
    String collectionBankId=request.getParameter("collectionBankId");
    String bizStatus=request.getParameter("bizStatus");
    String batch_num=request.getParameter("batch_num");
    String startDate=request.getParameter("startDate");  
    String endDate=request.getParameter("endDate");      
    String orgid=request.getParameter("orgid");
    String orgname=request.getParameter("orgname");
    String empid=request.getParameter("empid");
    String empname=request.getParameter("empname");
    
    if (!(officeCode == null || "".equals(officeCode))) {
      criterions.put("officeCode", officeCode);
    }
    
    if (!(collectionBankId == null || collectionBankId.length() == 0))
      criterions.put("collectionBankId", collectionBankId);
    
    if (!(bizStatus == null || bizStatus.length() == 0))
      criterions.put("bizStatus", bizStatus);

    if (!(batch_num == null || "".equals(batch_num))) {
      criterions.put("batch_num", batch_num);
    }
    
    if (!(startDate == null || startDate.length() == 0))
      criterions.put("startDate", startDate);
    
    if (!(endDate == null || endDate.length() == 0))
      criterions.put("endDate", endDate);
    
    if (!(orgid == null || orgid.length() == 0))
      criterions.put("orgid", orgid);
    
    if (!(orgname == null || orgname.length() == 0))
      criterions.put("orgname", orgname);
    
    if (!(empid == null || empid.length() == 0))
      criterions.put("empid", empid);
    
    if (!(empname == null || empname.length() == 0))
      criterions.put("empname", empname);
  
    Pagination pagination = new Pagination(0, 10, 1,
        "aa306.BATCH_NUM", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "collLoanbackcheckList";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.collloanbackcheck.action.CollLoanbackcheckShowAC";
  }

  protected HashMap makeCriterionsMap(CollLoanbackcheckAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



