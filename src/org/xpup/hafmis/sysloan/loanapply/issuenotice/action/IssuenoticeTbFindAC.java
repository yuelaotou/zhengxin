package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTbAF;

public class IssuenoticeTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IssuenoticeTbAF issuenoticeTbAF = (IssuenoticeTbAF) form;
      HashMap criterions = makeCriterionsMap(issuenoticeTbAF);
      Pagination pagination = new Pagination(0, 10, 1, "contractid", "DESC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      issuenoticeTbAF.reset(mapping, request);  
      request.getSession().setAttribute("find", "1");
      request.setAttribute("type", "1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetb_show");
  }

  protected String getPaginationKey() {
    return IssuenoticeTbShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(IssuenoticeTbAF form) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (!(contractId == null || contractId.length() == 0))
      m.put("contractId", form.getContractId().trim());
    String borrowerName = form.getBorrowerName();
    if (!(borrowerName == null || borrowerName.length() == 0)) {
      m.put("borrowerName", form.getBorrowerName().trim());
    }
    String cardNum = form.getCardNum();
    if (!(cardNum == null || cardNum.length() == 0)) {
      m.put("cardNum", form.getCardNum().trim());
    }
    String loanBankId = form.getLoanBankId();
    if (!(loanBankId == null || loanBankId.length() == 0)) {
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    String contractSt=form.getContractSt();
    if (!(contractSt == null || contractSt.length() == 0)) {
      m.put("contractSt", form.getContractSt().trim());
    }
    String isPrint=form.getIsPrint();
    if (!(isPrint == null || isPrint.length() == 0)) {
      m.put("isPrint", isPrint.trim());
    }
    return m;
  }
}
