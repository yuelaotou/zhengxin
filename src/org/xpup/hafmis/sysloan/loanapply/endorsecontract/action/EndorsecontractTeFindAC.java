package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;

public class EndorsecontractTeFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    
    EndorsecontractTeAF endorsecontractTeAF = (EndorsecontractTeAF)form;
    criterions = makeCriterionsMap(endorsecontractTeAF);
    Pagination pagination = new Pagination(0, 10, 1, "t1.contract_id", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    endorsecontractTeAF.reset(mapping, request);
    return mapping.findForward("to_endorsecontractTeShowAC");
  }
  protected String getPaginationKey() {

    return EndorsecontractTeShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(EndorsecontractTeAF form) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }

    String debitter = form.getDebitter();
    if (debitter != null && !"".equals(debitter)) {
      m.put("debitter", debitter.trim());
    }

    String empId = form.getEmpId();
    if (empId != null && !"".equals(empId)) {
      m.put("empId", empId.trim());
    }

    String cardNum = form.getCardNum();
    if (cardNum != null && !"".equals(cardNum)) {
      m.put("cardNum", cardNum.trim());
    }

    String bank = form.getBank();
    if (bank != null && !"".equals(bank)) {
      m.put("bank", bank.trim());
    }

    String loanTimeLimit = form.getLoanTimeLimit();
    if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
      m.put("loanTimeLimit", loanTimeLimit.trim());
    }

    String startSDate = form.getStartSDate();
    if (startSDate != null && !"".equals(startSDate)) {
      m.put("startSDate", startSDate.trim());
    }

    String startEDate = form.getStartEDate();
    if (startEDate != null && !"".equals(startEDate)) {
      m.put("startEDate", startEDate.trim());
    }

    String endSDate = form.getEndSDate();
    if (endSDate != null && !"".equals(endSDate)) {
      m.put("endSDate", endSDate.trim());
    }
    
    String endEDate = form.getEndEDate();
    if (endEDate != null && !"".equals(endEDate)) {
      m.put("endEDate", endEDate.trim());
    }
    String contractSt = form.getContractSt();
    if(contractSt != null && !"".equals(contractSt)){
      m.put("contractSt", contractSt.trim());
    }
    m.put("key", "value");
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
