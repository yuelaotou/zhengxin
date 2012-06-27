package org.xpup.hafmis.sysloan.loancallback.relievecontract.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.form.RelieveContractTbAF;

public class RelieveContractTbFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      RelieveContractTbAF relieveContractTbAF = (RelieveContractTbAF) form;
      HashMap criterions = makeCriterionsMap(relieveContractTbAF);
      Pagination pagination = new Pagination(0, 10, 1, "res.contractid", "DESC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      relieveContractTbAF.reset(mapping, request);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("relievecontracttb_show");
  }
  protected String getPaginationKey() {
    return RelieveContractTbShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(RelieveContractTbAF form) {
    HashMap map = new HashMap();
    String loanKouAcc = form.getLoanKouAcc();
    if (!(loanKouAcc == null || loanKouAcc.length() == 0)) {
      map.put("loanKouAcc", form.getLoanKouAcc().trim());
    }
    String contractId = form.getContractId();
    if (!(contractId == null || contractId.length() == 0))
      map.put("contractId", form.getContractId().trim());
    String borrowerName = form.getBorrowerName();
    if(!(borrowerName == null || borrowerName.length() == 0)){
      map.put("borrowerName", form.getBorrowerName().trim());
    }
    String cardNum = form.getCardNum();
    if(!(cardNum == null || cardNum.length() == 0)){
      map.put("cardNum", form.getCardNum().trim());
    }
    String loanBankId = form.getLoanBankId();
    if(!(loanBankId == null || loanBankId.length() == 0)){
      map.put("loanBankId", form.getLoanBankId().trim());
    } 
    return map;
  }
}
