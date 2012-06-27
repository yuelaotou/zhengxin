package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;

public class LoanCheckFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String findType = "find";
    LoanCheckShowAF loanCheckShowAF = (LoanCheckShowAF) form;
    HashMap criterions = makeCriterionsMap(loanCheckShowAF, findType);
    Pagination pagination = new Pagination(0, 10, 1, "p110.contract_id",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("loancheckShowAC");
  }

  protected HashMap makeCriterionsMap(LoanCheckShowAF form, String findType) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }
    String officeCode = form.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("officeCode", officeCode.trim());
    }
    String borrowerName = form.getBorrowerName().trim();
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum.trim());
    }
    String loanBankName = form.getLoanBankName();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName.trim());
    }
    String orgName = form.getOrgName();
    if (orgName != null && orgName.length() > 0) {
      m.put("orgName", orgName.trim());
    }
    String houseType = form.getHouseType();
    if (houseType != null && houseType.length() > 0) {
      m.put("houseType", houseType.trim());
    }
    String beginBizDate = form.getBeginBizDate();// 起始操作时间
    if (beginBizDate != null && beginBizDate.length() > 0) {
      m.put("beginBizDate", beginBizDate.trim());
    }
    String endBizDate = form.getEndBizDate();// 终止操作时间
    if (endBizDate != null && endBizDate.length() > 0) {
      m.put("endBizDate", endBizDate.trim());
    }
    String contractStFind = form.getContractStFind();
    if (contractStFind != null && contractStFind.length() > 0) {
      m.put("contractStFind", contractStFind.trim());
    }
    String beginBackDate = form.getBeginBackDate();// 终止操作时间
    if (beginBackDate != null && beginBackDate.length() > 0) {
      m.put("beginBackDate", beginBackDate.trim());
    }
    String endBackDate = form.getEndBackDate();
    if (endBackDate != null && endBackDate.length() > 0) {
      m.put("endBackDate", endBackDate.trim());
    }
    m.put("findType", findType);
    return m;
  }

  protected String getPaginationKey() {
    return LoanCheckShowAC.PAGINATION_KEY;
  }
}