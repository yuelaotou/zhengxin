package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form;

import org.apache.struts.action.ActionForm;

/**
 * @author Õı“∞ 2007-09-27
 */
public class LoanVIPCheckReasonAF extends ActionForm {

  private static final long serialVersionUID = -6548593753756361569L;

  private String contractId = null;

  private String buttonMethod = null;

  private String reason = null;

  public String getButtonMethod() {
    return buttonMethod;
  }

  public void setButtonMethod(String buttonMethod) {
    this.buttonMethod = buttonMethod;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

}
