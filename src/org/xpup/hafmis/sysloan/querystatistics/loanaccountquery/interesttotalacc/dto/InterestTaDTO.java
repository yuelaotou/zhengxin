package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.dto;

import java.math.BigDecimal;

public class InterestTaDTO {
    
  private String year = "";
  
  private String month = "";
  
  private String date = "";
  
  private String loanBank = "";
  
  private String loanBankId = "";
  
  private BigDecimal bgnInterest = new BigDecimal(0.00);
  
  private BigDecimal bgnOverdueInterest = new BigDecimal(0.00);
  
  private BigDecimal thisInterest = new BigDecimal(0.00);
  
  private BigDecimal thisOverdueInterest = new BigDecimal(0.00);
  
  private BigDecimal totalInterest = new BigDecimal(0.00);
  
  private BigDecimal totalOverdueInterest = new BigDecimal(0.00);

  public BigDecimal getBgnInterest() {
    return bgnInterest;
  }

  public void setBgnInterest(BigDecimal bgnInterest) {
    this.bgnInterest = bgnInterest;
  }

  public BigDecimal getBgnOverdueInterest() {
    return bgnOverdueInterest;
  }

  public void setBgnOverdueInterest(BigDecimal bgnOverdueInterest) {
    this.bgnOverdueInterest = bgnOverdueInterest;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public BigDecimal getThisInterest() {
    return thisInterest;
  }

  public void setThisInterest(BigDecimal thisInterest) {
    this.thisInterest = thisInterest;
  }

  public BigDecimal getThisOverdueInterest() {
    return thisOverdueInterest;
  }

  public void setThisOverdueInterest(BigDecimal thisOverdueInterest) {
    this.thisOverdueInterest = thisOverdueInterest;
  }

  public BigDecimal getTotalInterest() {
    return totalInterest;
  }

  public void setTotalInterest(BigDecimal totalInterest) {
    this.totalInterest = totalInterest;
  }

  public BigDecimal getTotalOverdueInterest() {
    return totalOverdueInterest;
  }

  public void setTotalOverdueInterest(BigDecimal totalOverdueInterest) {
    this.totalOverdueInterest = totalOverdueInterest;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  
}
