package org.xpup.hafmis.sysloan.loancallback.bankimports.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.bankimports.form.BankImportsTaAF;

public class BankImportsTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    BankImportsTaAF f = (BankImportsTaAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "loanFlowTail.loanKouAcc", "DESC", criterions);
    String paginationKey = BankImportsTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "bankImportsTaShowAC";
  }

  protected HashMap makeCriterionsMap(BankImportsTaAF form) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (!(contractId== null || "".equals(contractId))) {
        m.put("contractId",form.getContractId().trim());
    }
    String name = form.getBorrowerName();
    if (!(name == null || name.length() == 0))
      m.put("borrowerName", form.getBorrowerName().trim());

    String cardNum = form.getCardNum();
    if(!(cardNum == null || cardNum.length() == 0)){
      m.put("cardNum", form.getCardNum().trim());
    }

    String loanKouAcc = form.getLoanKouAcc();
    if (!(loanKouAcc == null || loanKouAcc.length() == 0)){
      m.put("loanKouAcc", form.getLoanKouAcc().trim());
    }
    
    String loanBankId = form.getLoanBankId();
    if (!(loanBankId == null || loanBankId.length() == 0)){
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    return m;
  }

}
