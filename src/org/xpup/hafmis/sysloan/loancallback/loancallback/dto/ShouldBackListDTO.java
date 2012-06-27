package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;

public class ShouldBackListDTO {

  //还款年月
  private String loanKouYearmonth = "";
  //还款类型
  private String loanKouType = "";
  //应还本金
  private BigDecimal shouldCorpus = new BigDecimal(0.00);
  //应还利息
  private BigDecimal shouldInterest = new BigDecimal(0.00);
  //逾期天数
  private String days = "";
  //未还罚息
  private BigDecimal punishInterest = new BigDecimal(0.00);
  //应还本息合计
  private BigDecimal ciMoney = new BigDecimal(0.00);
  //实还本息合计
  private BigDecimal realCiMoney = new BigDecimal(0.00);
  //每月利率
  private BigDecimal loanRate = new BigDecimal(0.00); 
  //实还本金
  private BigDecimal realCorpus = new BigDecimal(0.00);
  //实还利息
  private BigDecimal realInterest = new BigDecimal(0.00);
  //实还逾期本金
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);
  //实还逾期利息
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);
  //临时罚息
  private BigDecimal tempInterest = new BigDecimal(0.00);
  //记账日期
  private String bizDate = "";
  private String contractId = "";
  private String loanKouAcc ="";
  private String borrowerName = "";
  private String cardNum ="";
  private BigDecimal shouldPunishInterest = new BigDecimal(0.00);
  private BigDecimal realPunishInterest = new BigDecimal(0.00);
  private BigDecimal realMoney = new BigDecimal(0.00);
  private BigDecimal occurMoney = new BigDecimal(0.00);
  private BigDecimal money = new BigDecimal(0.00);
  
  private String loanRepayDay = "";
  private String counts="";
  private String id="";
  
  private String show_loanRate = "";
  //银行编号
  private String loanBankId = "";
  //银行名称
  private String loanBankName = "";
  //批次号
  private String batchNum = "";
  //是否为公积金还款
  private String yesOrNo = "";
  
  public String getYesOrNo() {
    return yesOrNo;
  }
  public void setYesOrNo(String yesOrNo) {
    this.yesOrNo = yesOrNo;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanBankName() {
    return loanBankName;
  }
  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }
  public BigDecimal getRealCiMoney() {
    return realCiMoney;
  }
  public void setRealCiMoney(BigDecimal realCiMoney) {
    this.realCiMoney = realCiMoney;
  }
  public String getShow_loanRate() {
    return show_loanRate;
  }
  public void setShow_loanRate(String show_loanRate) {
    this.show_loanRate = show_loanRate;
  }
  public BigDecimal getRealOverdueCorpus() {
    return realOverdueCorpus;
  }
  public void setRealOverdueCorpus(BigDecimal realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }
  public BigDecimal getRealOverdueInterest() {
    return realOverdueInterest;
  }
  public void setRealOverdueInterest(BigDecimal realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }
  public String getLoanRepayDay() {
    return loanRepayDay;
  }
  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }
  public String getCounts() {
    return counts;
  }
  public void setCounts(String counts) {
    this.counts = counts;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }
  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }
  public BigDecimal getShouldPunishInterest() {
    return shouldPunishInterest;
  }
  public void setShouldPunishInterest(BigDecimal shouldPunishInterest) {
    this.shouldPunishInterest = shouldPunishInterest;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public BigDecimal getCiMoney() {
    return ciMoney;
  }
  public void setCiMoney(BigDecimal ciMoney) {
    this.ciMoney = ciMoney;
  }
  public String getDays() {
    return days;
  }
  public void setDays(String days) {
    this.days = days;
  }
  public String getLoanKouType() {
    return loanKouType;
  }
  public void setLoanKouType(String loanKouType) {
    this.loanKouType = loanKouType;
  }
  public String getLoanKouYearmonth() {
    return loanKouYearmonth;
  }
  public void setLoanKouYearmonth(String loanKouYearmonth) {
    this.loanKouYearmonth = loanKouYearmonth;
  }
  public BigDecimal getLoanRate() {
    return loanRate;
  }
  public void setLoanRate(BigDecimal loanRate) {
    this.loanRate = loanRate;
  }
  public BigDecimal getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getRealCorpus() {
    return realCorpus;
  }
  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }
  public BigDecimal getRealInterest() {
    return realInterest;
  }
  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
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
  public BigDecimal getTempInterest() {
    return tempInterest;
  }
  public void setTempInterest(BigDecimal tempInterest) {
    this.tempInterest = tempInterest;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getBatchNum() {
    return batchNum;
  }
  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  
}