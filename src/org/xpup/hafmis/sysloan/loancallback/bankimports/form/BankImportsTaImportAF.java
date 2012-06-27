package org.xpup.hafmis.sysloan.loancallback.bankimports.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class BankImportsTaImportAF extends ActionForm {
  
  private static final long serialVersionUID = 157830469042818336L;
  
  private FormFile theFile = null;
  private String url="";
  private String loanBankId = "";
  private String loanKouAcc = "";
//ÐÕÃû
  private String borrowerName = "";
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public ActionErrors validate(ActionMapping mapping,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /** 
   * Method reset
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }
}