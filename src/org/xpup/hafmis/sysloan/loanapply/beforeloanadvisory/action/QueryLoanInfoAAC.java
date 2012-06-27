package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.bsinterface.IBeforeLoanAdvisoryBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;

public class QueryLoanInfoAAC  extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF=(BeforeLoanAdvisoryShowAF)form;
     String housetype=(String)request.getParameter("housetype");
     String totalPrice=(String)request.getParameter("totalPrice");
     IBeforeLoanAdvisoryBS  beforeLoanAdvisoryBS  = (IBeforeLoanAdvisoryBS) BSUtils.getBusinessService(
         "beforeLoanAdvisoryBS", this, mapping.getModuleConfig());
     beforeLoanAdvisoryShowAF=beforeLoanAdvisoryBS.queryLoanInfo(beforeLoanAdvisoryShowAF, housetype,totalPrice);
     String temploantimeLimit=beforeLoanAdvisoryShowAF.getMaxLoanYear();
     String monthRate="";
     String intes="0";
     if(temploantimeLimit!=null&&!temploantimeLimit.equals(""))
     {
       int tempLimit=Integer.parseInt(temploantimeLimit)*12;
       String loantimeLimit=tempLimit+"";
       monthRate = beforeLoanAdvisoryBS.findMonthRate(beforeLoanAdvisoryShowAF.getOfficecode(),loantimeLimit,null);
       BigDecimal monthRatebig=new BigDecimal(monthRate);
       String monthRatess=monthRatebig.multiply(new BigDecimal(100))+"%";
       BigDecimal inteset=new BigDecimal(0.00);
       inteset =CorpusinterestBS.getCorpusInterest(new BigDecimal(beforeLoanAdvisoryShowAF.getMaxLoanMoney()), new BigDecimal(monthRate), loantimeLimit);
       intes=inteset.toString();
     }
     
    
    
     String text="";
     text = "display('" + beforeLoanAdvisoryShowAF.getMaxLoanMoney() +"','" +beforeLoanAdvisoryShowAF.getMaxLoanYear() + "','" +intes+ "')";
     System.out.println(text);
     response.getWriter().write(text);
     response.getWriter().close();
     
     return null;
    
  }
}
