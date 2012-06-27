package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaChangeLoanMonthRateAF;

/**
 * 
 * @author yuqf
 * 2007-10-05
 */
public class EndorsecontractBankIdTaAccountAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    String text = null;
    String loanManthRate = "";
    String displayLoanMonthRate="";
    String corpusInterest="";
    try {
      String term = (String) request.getParameter("term");
      String bankId = (String) request.getParameter("bankId");
      String lastLoanMonthRate=(String) request.getParameter("realMonthInt");
      String loanMode = (String) request.getParameter("loanMode");
      String loanMoney = (String) request.getParameter("loanMoney");
      if(bankId!=null&&!bankId.equals("")){
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
      .getBusinessService("endorsecontractBS", this, mapping
          .getModuleConfig());
      EndorsecontractTaChangeLoanMonthRateAF ectcAF=new EndorsecontractTaChangeLoanMonthRateAF();
      ectcAF=endorsecontractBS.queryLoanMonthRate(bankId,term,lastLoanMonthRate,loanMode,loanMoney);
      loanManthRate=ectcAF.getLoanMonthRate().toString();
      displayLoanMonthRate=ectcAF.getLoanMonthRate().multiply(new BigDecimal(100.00)).toString()+"%";
      corpusInterest=ectcAF.getCorpusInterest();
      }else{
        loanManthRate=lastLoanMonthRate;
        displayLoanMonthRate=new BigDecimal(lastLoanMonthRate).multiply(new BigDecimal(100.00)).toString()+"%";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    text = "displays3('" + loanManthRate + "','" + displayLoanMonthRate+ "','" + corpusInterest + "')";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}
