package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto;

import java.math.BigDecimal;

public class PrincipalTaDTO {
    
  private String year = "";
  
  private String month = "";
  
  private String date = "";
  
  private String loanBank = "";
  
  private String loanBankId = "";
  
  private BigDecimal bgnBalance = new BigDecimal(0.00);
  
  private BigDecimal thisDebitMoney = new BigDecimal(0.00);
  
  private BigDecimal thisCreditMoney = new BigDecimal(0.00);
  
  private Integer thisDebitCount = new Integer(0);
  
  private Integer thisCreditCount = new Integer(0);
  
  private BigDecimal endBalance = new BigDecimal(0.00);
  
  private BigDecimal totalDebitMoney = new BigDecimal(0.00);
  
  private BigDecimal totalCreditMoney = new BigDecimal(0.00);
  
  private Integer totalDebitCount = new Integer(0);
  
  private Integer totalCreditCount = new Integer(0);

  public BigDecimal getBgnBalance() {
    return bgnBalance;
  }

  public void setBgnBalance(BigDecimal bgnBalance) {
    this.bgnBalance = bgnBalance;
  }

  public BigDecimal getEndBalance() {
    return endBalance;
  }

  public void setEndBalance(BigDecimal endBalance) {
    this.endBalance = endBalance;
  }

  public Integer getThisCreditCount() {
    return thisCreditCount;
  }

  public void setThisCreditCount(Integer thisCreditCount) {
    this.thisCreditCount = thisCreditCount;
  }

  public BigDecimal getThisCreditMoney() {
    return thisCreditMoney;
  }

  public void setThisCreditMoney(BigDecimal thisCreditMoney) {
    this.thisCreditMoney = thisCreditMoney;
  }

  public Integer getThisDebitCount() {
    return thisDebitCount;
  }

  public void setThisDebitCount(Integer thisDebitCount) {
    this.thisDebitCount = thisDebitCount;
  }

  public BigDecimal getThisDebitMoney() {
    return thisDebitMoney;
  }

  public void setThisDebitMoney(BigDecimal thisDebitMoney) {
    this.thisDebitMoney = thisDebitMoney;
  }

  public Integer getTotalCreditCount() {
    return totalCreditCount;
  }

  public void setTotalCreditCount(Integer totalCreditCount) {
    this.totalCreditCount = totalCreditCount;
  }

  public BigDecimal getTotalCreditMoney() {
    return totalCreditMoney;
  }

  public void setTotalCreditMoney(BigDecimal totalCreditMoney) {
    this.totalCreditMoney = totalCreditMoney;
  }

  public Integer getTotalDebitCount() {
    return totalDebitCount;
  }

  public void setTotalDebitCount(Integer totalDebitCount) {
    this.totalDebitCount = totalDebitCount;
  }

  public BigDecimal getTotalDebitMoney() {
    return totalDebitMoney;
  }

  public void setTotalDebitMoney(BigDecimal totalDebitMoney) {
    this.totalDebitMoney = totalDebitMoney;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }
  
  
}
