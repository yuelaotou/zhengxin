/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.arithmetic.entireyearmoney.EntireYearMoneyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;

/** 
 * MyEclipse Struts
 * Creation date: 10-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoanapplyTdFindMonthRateAAC extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
   String office=(String) request.getParameter("office");
   String loantimeLimit=(String) request.getParameter("loantimeLimit");
   String loanMoney=(String) request.getParameter("loanMoney");
  // String loanMonthRate=(String) request.getParameter("loanMonthRate");
   String loanMood=(String) request.getParameter("loanMood");
   
   try {
    String monthRate=loanapplyBS.findMonthRate(office,loantimeLimit,loanMood);
    BigDecimal monthRatebig = new BigDecimal(0.00);
    if(monthRate!=null && !"".equals(monthRate)){
     monthRatebig=new BigDecimal(monthRate);
    }
    String monthRatess=monthRatebig.multiply(new BigDecimal(100))+"%";
    String intes="0";
    BigDecimal inteset=new BigDecimal(0.00);
    if(loanMood.equals("2")&&!loanMoney.equals("")){
       inteset =CorpusinterestBS.getCorpusInterest(new BigDecimal(loanMoney), new BigDecimal(monthRate), loantimeLimit);
       intes=inteset.toString();
    }else if((loanMood.equals("4")||loanMood.equals("5"))&&!loanMoney.equals("")){
      inteset=EntireYearMoneyBS.getEntireYearMoney(new BigDecimal(loanMoney), new BigDecimal(monthRate), loantimeLimit);
      intes=inteset.toString();
    }
    String text = "";
    text = "display1('" +monthRate + "','" +monthRatess + "','" +intes+ "');";
    response.getWriter().write(text);
    response.getWriter().close();
  } catch (BusinessException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
    
    
		return null;
	}
}