package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTbAF;

public class LoanreturnedbyfundTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      LoanreturnedbyfundTbAF loanreturnedbyfundTbAF = (LoanreturnedbyfundTbAF) form;
      HashMap criterions = makeCriterionsMap(loanreturnedbyfundTbAF);
      Pagination pagination = new Pagination(0, 10, 1, "a.reservea_a,a.contract_id,a.seq",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_showloanreturnedbyfundTb");
  }

  protected String getPaginationKey() {
    return LoanreturnedbyfundTbShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(LoanreturnedbyfundTbAF form) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (!(contractId == null || contractId.trim().length() == 0))
      m.put("contractId", form.getContractId().trim());
    String borrowerName = form.getBorrowerName();
    if (!(borrowerName == null || borrowerName.trim().length() == 0)) {
      m.put("borrowerName", form.getBorrowerName().trim());
    }
    String cardNum = form.getCardNum();
    if (!(cardNum == null || cardNum.trim().length() == 0)) {
      m.put("cardNum", form.getCardNum().trim());
    }

    String xieYiNUM = form.getXieYiNum();
    if (!(xieYiNUM == null || xieYiNUM.trim().length() == 0))
      m.put("xieYiNUM", form.getXieYiNum().trim());
    String assiBorrowerName = form.getAssiBorrowerName();
    if (!(assiBorrowerName == null || assiBorrowerName.trim().length() == 0)) {
      m.put("assiBorrowerName", form.getAssiBorrowerName().trim());
    }
    String loanBankId = form.getLoanBankId();
    if (loanBankId != null) {
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    String assiBorrowerCardNum = form.getAssiBorrowerCardNum();
    if (!(assiBorrowerCardNum == null || assiBorrowerCardNum.trim().length() == 0)) {
      m.put("assiBorrowerCardNum", form.getAssiBorrowerCardNum().trim());
    }
    String orgId = form.getOrgId();
    if (!(orgId == null || orgId.trim().length() == 0))
      m.put("orgId", form.getOrgId().trim());
    String empId = form.getEmpId();
    if (!(empId == null || empId.trim().length() == 0)) {
      m.put("empId", form.getEmpId().trim());
    }
    String startDate = form.getStartDate();
    if (!(startDate == null || startDate.trim().length() == 0)) {
      m.put("startDate", form.getStartDate().trim());
    }
    String endDate = form.getEndDate();
    if (!(endDate == null || endDate.trim().length() == 0)) {
      m.put("endDate", form.getEndDate().trim());
    }
    String begstop = form.getBegstop();
    if (!(begstop == null || begstop.trim().length() == 0)) {
      m.put("begstop", begstop.trim());
    }
    String endstop = form.getEndstop();
    if (!(endstop == null || endstop.trim().length() == 0)) {
      m.put("endstop", endstop.trim());
    }
    String sta = form.getSta();
    if (!(sta == null || sta.trim().length() == 0)) {
      m.put("sta", sta.trim());
    }
    return m;
  }
}
