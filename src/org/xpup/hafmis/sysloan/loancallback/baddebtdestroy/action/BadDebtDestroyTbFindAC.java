package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTbAF;


public class BadDebtDestroyTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    BadDebtDestroyTbAF f = (BadDebtDestroyTbAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "loanFlowHead.flowHeadId", "DESC", criterions);
    String paginationKey = BadDebtDestroyTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "badDebtDestroyTbShowAC";
  }
  protected HashMap makeCriterionsMap(BadDebtDestroyTbAF form) {
    HashMap m = new HashMap();   
    String contractId = form.getContractId();
    if (!(contractId== null || "".equals(contractId))) {
        m.put("contractId",form.getContractId().trim());
    }
    String name = form.getBorrowerName();
    if (!(name == null || name.length() == 0))
      m.put("name", form.getBorrowerName().trim());   
    String status = form.getStatus();
    if(!(status == null || status.length() == 0)){
      m.put("status", form.getStatus().trim());
    }
    String cardNum = form.getCardNum();
    if(!(cardNum == null || cardNum.length() == 0)){
      m.put("cardNum", form.getCardNum().trim());
    }
    String loanBankId = form.getLoanBankId();
    if(!(loanBankId == null || loanBankId.length() == 0)){
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    String loanKouAcc = form.getLoanKouAcc();
    if (!(loanKouAcc == null || loanKouAcc.length() == 0))
      m.put("loanKouAcc", form.getLoanKouAcc().trim());
    
    String docNum = form.getDocNum();
    if(!(docNum == null || docNum.length() == 0)){
      m.put("docNum", form.getDocNum().trim());
    }        
    return m;
  }

}
