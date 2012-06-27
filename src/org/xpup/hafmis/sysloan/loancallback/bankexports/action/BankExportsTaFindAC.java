package org.xpup.hafmis.sysloan.loancallback.bankexports.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.bankexports.form.BankExportsTaAF;

public class BankExportsTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    BankExportsTaAF f = (BankExportsTaAF) form;
    String bankId=f.getBankId();
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination1 = getPagination(BankExportsTaShowAC.PAGINATION_KEY, request); 
    String yearMonth = (String)pagination1.getQueryCriterions().get("yearMonth");
    String day = (String)pagination1.getQueryCriterions().get("day");
    String loanBankId = (String)pagination1.getQueryCriterions().get("loanBankId");
    String batchNum = (String)pagination1.getQueryCriterions().get("batchNum");
    
    if(loanBankId==null&&bankId!=null){
      loanBankId=bankId;
    }
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "loanFlowTail.loanKouAcc", "DESC", criterions);
    pagination.getQueryCriterions().put("yearMonth",yearMonth);
    pagination.getQueryCriterions().put("day",day);
    pagination.getQueryCriterions().put("loanBankId",loanBankId);
    pagination.getQueryCriterions().put("batchNum",batchNum);
    
    String paginationKey = BankExportsTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "bankExportsTaShowAC";
  }

  protected HashMap makeCriterionsMap(BankExportsTaAF form) {
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
   
    String fund_st = form.getFund_st();
    if (!(fund_st == null || fund_st.length() == 0)){
      m.put("fund_st", form.getFund_st().trim());
    }
    
    return m;
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "loanFlowTail.loanKouAcc", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
