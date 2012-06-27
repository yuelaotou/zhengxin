package org.xpup.hafmis.sysloan.specialbiz.bailpickup.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.paymng.paysure.action.ShowPaymentHeadAC;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTbAF;

public class BailpickupTbFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
   
  BailpickupTbAF f = (BailpickupTbAF) form;
  HashMap criterions = makeCriterionsMap(f);
  Pagination pagination = new Pagination(0, 10, 1, "t3.flow_head_id ", "DESC",
      criterions);
  String paginationKey = getPaginationKey();
  request.getSession().setAttribute(paginationKey, pagination);
  modifyPagination(pagination);
  f.reset(mapping, request);
  return mapping.findForward("to_bailpickupTbShowAC");
}

protected String getPaginationKey() {
  return BailpickupTbShowAC.PAGINATION_KEY;
}

protected HashMap makeCriterionsMap(BailpickupTbAF form) {
  HashMap m = new HashMap();
  String loanKouAcc = form.getLoanKouAcc();
  if (!(loanKouAcc == null || "".equals(loanKouAcc))) {
      m.put("loanKouAcc", loanKouAcc.trim());
  }

  String contractId = form.getContractId();
  if (!(contractId == null || "".equals(contractId))){
    m.put("contractId", contractId.trim());
  }
  String borrowerName = form.getBorrowerName();
  if (!(borrowerName == null || "".equals(borrowerName))){
    m.put("borrowerName", borrowerName.trim());
  }
  String cardNum = form.getCardNum();
  if (!(cardNum == null || "".equals(cardNum))){
    m.put("cardNum", cardNum.trim());
  }
  String loanBank = form.getLoanBank();
  if (!(loanBank == null || "".equals(loanBank))){
    m.put("loanBank", loanBank.trim());
  }
  String bizStatus = form.getBizStatus();
  if (!(bizStatus == null || "".equals(bizStatus))){
    m.put("bizStatus", bizStatus.trim());
  }
  m.put("key", "value");
  return m;
}

protected void modifyPagination(Pagination pagination) {
}
}
