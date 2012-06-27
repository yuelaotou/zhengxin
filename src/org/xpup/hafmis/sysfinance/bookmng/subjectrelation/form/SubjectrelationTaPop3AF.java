package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class SubjectrelationTaPop3AF extends ActionForm {
  /*
   * µ¥Î»list
   */
  private List list = null;

  private String loanBankId = "";

  private String office = "";

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }
}
