package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.bsinterface.IBeforeLoanAdvisoryBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;

public class QueryInputLoanInfoAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String salary=(String)request.getParameter("inputSalary");
    String housetype=(String)request.getParameter("housetype");
    BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF=(BeforeLoanAdvisoryShowAF)form;
    IBeforeLoanAdvisoryBS  beforeLoanAdvisoryBS  = (IBeforeLoanAdvisoryBS) BSUtils.getBusinessService(
        "beforeLoanAdvisoryBS", this, mapping.getModuleConfig());
    beforeLoanAdvisoryShowAF=beforeLoanAdvisoryBS.queryIputLoanInfo(beforeLoanAdvisoryShowAF, 
        salary, housetype);
    
    String text="";
    text = "dis('" + beforeLoanAdvisoryShowAF.getOutLoanYear() +"','" +beforeLoanAdvisoryShowAF.getOutLoanMoney()+"')";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }

}
