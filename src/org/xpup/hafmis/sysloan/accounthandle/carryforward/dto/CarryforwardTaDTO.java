package org.xpup.hafmis.sysloan.accounthandle.carryforward.dto;

import java.math.BigDecimal;

public class CarryforwardTaDTO {
  
  private String contractId = ""; // 合同编号

  private String loanKouAcc = ""; // 贷款账号

  private String borrowerName = ""; // 借款人姓名

  private BigDecimal newLoanLastMoney = new BigDecimal(0.00); // 新贷款余额

  private BigDecimal newLoanlastTimeLimit = new BigDecimal(0.00); // 新剩余期限

  private BigDecimal loanMonthRate = new BigDecimal(0.00); // 月利率
  
  private String temploanMonthRate ; // 显示用的月利率

  private String newLoanInterest = ""; // 月还本息
  
  private String loanMode="";   //还款方式
  
  private String loanBankName=""; //银行名称

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getNewLoanInterest() {
    return newLoanInterest;
  }

  public void setNewLoanInterest(String newLoanInterest) {
    this.newLoanInterest = newLoanInterest;
  }

  public BigDecimal getNewLoanLastMoney() {
    return newLoanLastMoney;
  }

  public void setNewLoanLastMoney(BigDecimal newLoanLastMoney) {
    this.newLoanLastMoney = newLoanLastMoney;
  }

  public BigDecimal getNewLoanlastTimeLimit() {
    return newLoanlastTimeLimit;
  }

  public void setNewLoanlastTimeLimit(BigDecimal newLoanlastTimeLimit) {
    this.newLoanlastTimeLimit = newLoanlastTimeLimit;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }
}
