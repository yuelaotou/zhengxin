package org.xpup.hafmis.sysloan.loancallback.bankexports.dto;

import java.math.BigDecimal;

public class BankExportsDTO {

  //会计日期
  private String bizDate = "";
  //还款年月
  private String loanKouYearmonth = "";
  //应扣金额
  private BigDecimal shouldMoney = new BigDecimal(0.00);
  //实扣金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  //姓名
  private String borrowerName = "";
  //放款银行
  private String loanBankId = "";
  //扣款账号
  private String loanKouAcc ="";
  //扣款标识
  private String identifier = "";
  //批次号
  private String batchNum = "";
  public String getBizDate() {
    return bizDate;
  }
  
  
  public BigDecimal getRealMoney() {
    return realMoney;
  }


  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }


  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getIdentifier() {
    return identifier;
  }
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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
  public String getLoanKouYearmonth() {
    return loanKouYearmonth;
  }
  public void setLoanKouYearmonth(String loanKouYearmonth) {
    this.loanKouYearmonth = loanKouYearmonth;
  }
  public BigDecimal getShouldMoney() {
    return shouldMoney;
  }
  public void setShouldMoney(BigDecimal shouldMoney) {
    this.shouldMoney = shouldMoney;
  }


  public String getBatchNum() {
    return batchNum;
  }


  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }
  
  
  
}