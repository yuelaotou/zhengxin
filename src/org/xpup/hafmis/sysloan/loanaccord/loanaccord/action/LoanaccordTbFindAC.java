package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public class LoanaccordTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanaccordShowAF loanaccordShowAF = (LoanaccordShowAF) form;
    HashMap criterions = makeCriterionsMap(loanaccordShowAF);
    String contractId = loanaccordShowAF.getContractIdf().trim();
    String borrowerName = loanaccordShowAF.getBorrowerNamef().trim();
    String cardNum = loanaccordShowAF.getCardNumf().trim();
    String loanBankId = loanaccordShowAF.getLoanBankIdf().trim();
    String bizSt = loanaccordShowAF.getBizStf();
    String loanStartDate = loanaccordShowAF.getLoanStartDate();
    String loanEndDate = loanaccordShowAF.getLoanEndDate();
    if (!(contractId == null || "".equals(contractId))) {
      criterions.put("contractId", contractId);
    }
    if (!(borrowerName == null || borrowerName.length() == 0))
      criterions.put("borrowerName", borrowerName);

    if (!(cardNum == null || cardNum.length() == 0))
      criterions.put("cardNum", cardNum);

    if (!(loanBankId == null || loanBankId.length() == 0))
      criterions.put("loanBankId", loanBankId);

    if (!(bizSt == null || bizSt.length() == 0))
      criterions.put("bizSt", bizSt);
    if (bizSt == null || bizSt.length() == 0) {
      criterions.put("bizSt", "456");
    }
    if (loanStartDate != null) {
      criterions.put("loanStartDate", loanStartDate.trim());
    }
    if (loanEndDate != null) {
      criterions.put("loanEndDate", loanEndDate.trim());
    }
    Pagination pagination = new Pagination(0, 10, 1, "borrower.contractId",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);

    loanaccordShowAF.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "loanaccordTbShowAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.loanaccord.loanaccord.action.LoanaccordTbShowAC";
  }

  protected HashMap makeCriterionsMap(LoanaccordShowAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
