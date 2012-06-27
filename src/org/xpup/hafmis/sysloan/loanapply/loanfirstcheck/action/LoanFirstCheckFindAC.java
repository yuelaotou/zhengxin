package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.form.LoanFirstCheckShowAF;

public class LoanFirstCheckFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanFirstCheckShowAF af = (LoanFirstCheckShowAF) form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "p110.contract_id",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("loanFirstCheckShowAC");
  }

  protected HashMap makeCriterionsMap(LoanFirstCheckShowAF form) {
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
    String orgName = form.getOrgName();
    if (orgName != null && orgName.length() > 0) {
      m.put("orgName", orgName.trim());
    }
    String houseType = form.getHouseType();
    if (houseType != null && houseType.length() > 0) {
      m.put("houseType", houseType.trim());
    }
    String opTimeSt = form.getOpTimeSt();// 起始操作时间
    if (opTimeSt != null && opTimeSt.length() > 0) {
      m.put("opTimeSt", opTimeSt.trim());
    }
    String opTimeEnd = form.getOpTimeEnd();// 终止操作时间
    if (opTimeEnd != null && opTimeEnd.length() > 0) {
      m.put("opTimeEnd", opTimeEnd.trim());
    }
    String contractSt = form.getContractSt();// 合同状态
    if (contractSt != null && contractSt.length() > 0) {
      m.put("contractSt", contractSt.trim());
    }
    return m;
  }

  protected String getPaginationKey() {
    return LoanFirstCheckShowAC.PAGINATION_KEY;
  }
}