package org.xpup.hafmis.sysloan.dataready.loanpara.form;

import org.apache.struts.action.ActionForm;
/**
 * @author ั๎นโ 
 */
public class LoanDocNumDesignAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private String loanDocNumDocument="";
  private String name="";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLoanDocNumDocument() {
    return loanDocNumDocument;
  }

  public void setLoanDocNumDocument(String loanDocNumDocument) {
    this.loanDocNumDocument = loanDocNumDocument;
  }
}
