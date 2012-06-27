package org.xpup.hafmis.sysloan.loanapply.personalloancalc.form;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PersonalloancalcFindAF extends ActionForm {

  private String loanTimeLimit;

  private String loanRate;

  private String loanMoney;

  private Map months;

  public Map getMonths() {
    return months;
  }

  public void setMonths(Map months) {
    this.months = months;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanRate() {
    return loanRate;
  }

  public void setLoanRate(String loanRate) {
    this.loanRate = loanRate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.loanMoney = "";
    this.loanRate = "";
    this.loanTimeLimit = "";
  }
}
