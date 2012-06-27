package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto;

public class PrincipalAccInfDTO {
  // 这个类用于封装pl202中的数据
  
  private String loanBankId;
  
  private String year;
  
  private String month;
  
  private String date;
  
  private String occurMoney;
  
  private String realCorpus;
  
  private String realOverdueCorpus;
  
  private String shouldCount;
  
  private String bizType;
  
  private String wrongBizType;

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

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }

  public String getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(String realCorpus) {
    this.realCorpus = realCorpus;
  }

  public String getRealOverdueCorpus() {
    return realOverdueCorpus;
  }

  public void setRealOverdueCorpus(String realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }

  public String getShouldCount() {
    return shouldCount;
  }

  public void setShouldCount(String shouldCount) {
    this.shouldCount = shouldCount;
  }

  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }
  
}
