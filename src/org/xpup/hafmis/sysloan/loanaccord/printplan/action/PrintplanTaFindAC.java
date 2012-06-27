package org.xpup.hafmis.sysloan.loanaccord.printplan.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;

public class PrintplanTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      {
    try{
    PrintplanShowAF printplanShowAF = (PrintplanShowAF) form;      
    HashMap criterions = makeCriterionsMap(printplanShowAF);
    String contractId=request.getParameter("contractId");
    if (!(contractId == null || "".equals(contractId))) {
      criterions.put("contractId", contractId);
    }
    Pagination pagination = new Pagination(0, 10, 1,
        "restoreLoan.loanKouYearmonth", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    printplanShowAF.reset(mapping, request);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "printplanTaShowAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.loanaccord.printplan.action.PrintplanTaShowAC";
  }

  protected HashMap makeCriterionsMap(PrintplanShowAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
