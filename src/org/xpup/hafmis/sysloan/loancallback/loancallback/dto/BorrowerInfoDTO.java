package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;

public class BorrowerInfoDTO {
  //姓名
  private String borrowerName = "";
  //证件类型
  private String cardKind = "";
  //证件号码
  private String cardNum = "";
  //剩余本金//贷款余额
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00); 
  //还款方式
  private String loanMode = "";
  //合同编号
  private String contractId = "";
  //贷款账号
  private String loanKouAcc = "";
  //还款日
  private String loanRepayDay = "";
  //挂账余额
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);
  //放款银行
  private Integer loanBankId = new Integer(0);
  //发放日期
  private String loanStartDate = "";
  //贷款期限
  private String loanTimeLimit = "";
  //办事处
  private String officeCode = "";
  
  // 吴洪涛修改//2007-3-11 
  String loanInterestMonth = "";// 月利息
  // 吴洪涛修改//2007-3-11 
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getLoanRepayDay() {
    return loanRepayDay;
  }
  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public Integer getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(Integer loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanStartDate() {
    return loanStartDate;
  }
  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }
  public String getLoanInterestMonth() {
    return loanInterestMonth;
  }
  public void setLoanInterestMonth(String loanInterestMonth) {
    this.loanInterestMonth = loanInterestMonth;
  }

  
}