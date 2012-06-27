package org.xpup.hafmis.sysloan.loanapply.loandelete.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loandelete.form.LoandeleteAF;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.action.LoanerlogoutTbShowAC;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.form.LoanerlogoutTbAF;
/**
 * ´û¿î»§É¾³ý
 * @author yqf
 *20081015
 */
public class LoandeleteFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try{
      LoandeleteAF loandeleteAF = (LoandeleteAF) form;
      HashMap criterions = makeCriterionsMap(loandeleteAF);
      Pagination pagination = new Pagination(0, 10, 1, " a.contract_id ", "DESC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      loandeleteAF.reset(mapping, request);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("to_loandeleteShowAC");
  }
  protected String getPaginationKey() {
    return LoandeleteShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(LoandeleteAF form) {
    HashMap m = new HashMap();
    String loanKouAcc = form.getLoanKouAcc();
    if (!(loanKouAcc == null || loanKouAcc.length() == 0)) {
      m.put("loanKouAcc", form.getLoanKouAcc().trim());
    }
    String contractId = form.getContractId();
    if (!(contractId == null || contractId.length() == 0))
      m.put("contractId", form.getContractId().trim());
    String borrowerName = form.getBorrowerName();
    if(!(borrowerName == null || borrowerName.length() == 0)){
      m.put("borrowerName", form.getBorrowerName().trim());
    }
    String cardNum = form.getCardNum();
    if(!(cardNum == null || cardNum.length() == 0)){
      m.put("cardNum", form.getCardNum().trim());
    }
    String loanBankId = form.getLoanBankId();
    if(!(loanBankId == null || loanBankId.length() == 0)){
      m.put("loanBankId", form.getLoanBankId().trim());
    } 
    return m;
  }

}
