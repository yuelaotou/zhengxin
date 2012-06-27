package org.xpup.hafmis.sysloan.loancallback.relievecontract.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class RelieveContractTbAF extends ActionForm{
  private List list=null;
  private String loanKouAcc="";
  private String contractId="";
  private String borrowerName="";
  private String cardNum="";
  private String loanBankId="";
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
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
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public void reset() {
    this.loanKouAcc = "";
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.loanBankId = "";
  }
}
