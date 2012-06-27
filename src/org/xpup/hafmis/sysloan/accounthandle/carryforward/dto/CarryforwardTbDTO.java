package org.xpup.hafmis.sysloan.accounthandle.carryforward.dto;

import java.math.BigDecimal;

public class CarryforwardTbDTO {
  
  private String loanBankId = ""; // 放款银行
  
  private String loanBankName=""; // 银行名称

  private String loanKouAcc = ""; // 贷款账号
  
  private String contractId = ""; // 合同编号

  private String borrowerName = ""; // 借款人姓名
  
  private String loanKouMonth = ""; //还款年月

  private BigDecimal shouldCorpus = new BigDecimal(0.00); // 应还本金

  private BigDecimal shouldInterest = new BigDecimal(0.00); // 应还利息

  private BigDecimal newLoanInterest = new BigDecimal(0.00); // 月还本息

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
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

  public String getLoanKouMonth() {
    return loanKouMonth;
  }

  public void setLoanKouMonth(String loanKouMonth) {
    this.loanKouMonth = loanKouMonth;
  }

  public BigDecimal getNewLoanInterest() {
    return newLoanInterest;
  }

  public void setNewLoanInterest(BigDecimal newLoanInterest) {
    this.newLoanInterest = newLoanInterest;
  }

  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }

  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }

  public BigDecimal getShouldInterest() {
    return shouldInterest;
  }

  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

}
