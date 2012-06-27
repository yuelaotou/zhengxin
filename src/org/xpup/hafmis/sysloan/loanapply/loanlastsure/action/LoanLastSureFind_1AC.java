package org.xpup.hafmis.sysloan.loanapply.loanlastsure.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;


public class LoanLastSureFind_1AC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String findType = "find";
    LoanLastSureShowAF loanLastSureShowAF = (LoanLastSureShowAF) form;
    HashMap criterions = makeCriterionsMap(loanLastSureShowAF, findType);
    Pagination pagination = new Pagination(0, 10, 1, "p110.contract_id",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("loanLastSureShowAC");
  }

  protected HashMap makeCriterionsMap(LoanLastSureShowAF form, String findType) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }
    String borrowerName = form.getBorrowerName();
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName.trim());
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum.trim());
    }
    String officeCode = form.getOfficeCode();
    if (officeCode != null && officeCode.length() > 0) {
      m.put("officeCode", officeCode.trim());
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
    String begindate = form.getBegindate();// 终审日期
    if (begindate != null && begindate.length() > 0) {
      m.put("begindate", begindate.trim());
    }
    String enddate = form.getEnddate();// 终审日期
    if (enddate != null && enddate.length() > 0) {
      m.put("enddate", enddate.trim());
    }
    String contractStFind = form.getContractStFind();
    if (contractStFind != null && contractStFind.length() > 0) {
      m.put("contractStFind", contractStFind.trim());
    }
    m.put("findType", findType);
    return m;
  }

  protected String getPaginationKey() {
    return LoanLastSureShowAC.PAGINATION_KEY;
  }
}