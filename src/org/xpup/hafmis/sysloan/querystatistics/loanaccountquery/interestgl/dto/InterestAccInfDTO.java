package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto;

public class InterestAccInfDTO {
  // 这个类用于封装pl202中的数据

  private String loanBankId;

  private String loanBank;

  private String year;

  private String month;

  private String date;

  private String bgnInterest = "";// 期初利息

  private String bgnOverdueInterest = "";// 期初逾期利息

  private String endInterest = "";// 期末利息

  private String endOverdueInterest = "";// 期末逾期利息

  private String realInterest;

  private String realOverdueInterest;

  private String bizType;

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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

  public String getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }

  public String getRealOverdueInterest() {
    return realOverdueInterest;
  }

  public void setRealOverdueInterest(String realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
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



  public String getBgnInterest() {
    return bgnInterest;
  }

  public void setBgnInterest(String bgnInterest) {
    this.bgnInterest = bgnInterest;
  }

  public String getBgnOverdueInterest() {
    return bgnOverdueInterest;
  }

  public void setBgnOverdueInterest(String bgnOverdueInterest) {
    this.bgnOverdueInterest = bgnOverdueInterest;
  }

  public String getEndInterest() {
    return endInterest;
  }

  public void setEndInterest(String endInterest) {
    this.endInterest = endInterest;
  }

  public String getEndOverdueInterest() {
    return endOverdueInterest;
  }

  public void setEndOverdueInterest(String endOverdueInterest) {
    this.endOverdueInterest = endOverdueInterest;
  }

}
