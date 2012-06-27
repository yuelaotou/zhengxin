package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoancallbackTaDTO {
  //姓名
  private String borrowerName = "";
  //证件类型
  private String cardKind = "";
  //证件号码
  private String cardNum = "";
  //剩余本金
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00); 
  //剩余期限
  private String overplusLimite = "";
  //还款方式
  private String loanMode = "";
  //合同编号
  private String contractId = "";
  //贷款账号
  private String loanKouAcc = "";
  //还至年月
  private List monthYearList = new ArrayList();
  //应还信息
  private List shouldBackList = new ArrayList();
  //还款类型
  private String payType = "";
  //提前还款本金
  private BigDecimal aheadCorpus = new BigDecimal(0.00);
  //占用天数
  private String days = "";
  //提前还款利息
  private BigDecimal aheadInterest = new BigDecimal(0.00);
  //手续费金额
  private BigDecimal loanPoundageMoney = new BigDecimal(0.00);
  //提前还款后剩余期限
  private String deadLine = "";
  //提前还款后月还本息
  private BigDecimal corpusInterest = new BigDecimal(0.00);
  //本次总还款本金
  private BigDecimal sumCorpus = new BigDecimal(0.00);
  //本次总还款利息
  private BigDecimal sumInterest = new BigDecimal(0.00);
  //本次总还款金额
  private BigDecimal sumMoney = new BigDecimal(0.00);
  //挂账余额 
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);
  //本次实收金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  public BigDecimal getAheadCorpus() {
    return aheadCorpus;
  }
  public void setAheadCorpus(BigDecimal aheadCorpus) {
    this.aheadCorpus = aheadCorpus;
  }
  public BigDecimal getAheadInterest() {
    return aheadInterest;
  }
  public void setAheadInterest(BigDecimal aheadInterest) {
    this.aheadInterest = aheadInterest;
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
  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getDays() {
    return days;
  }
  public void setDays(String days) {
    this.days = days;
  }
  public String getDeadLine() {
    return deadLine;
  }
  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
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
  public BigDecimal getLoanPoundageMoney() {
    return loanPoundageMoney;
  }
  public void setLoanPoundageMoney(BigDecimal loanPoundageMoney) {
    this.loanPoundageMoney = loanPoundageMoney;
  }
  public List getMonthYearList() {
    return monthYearList;
  }
  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getOverplusLimite() {
    return overplusLimite;
  }
  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }
  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getPayType() {
    return payType;
  }
  public void setPayType(String payType) {
    this.payType = payType;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public List getShouldBackList() {
    return shouldBackList;
  }
  public void setShouldBackList(List shouldBackList) {
    this.shouldBackList = shouldBackList;
  }
  public BigDecimal getSumCorpus() {
    return sumCorpus;
  }
  public void setSumCorpus(BigDecimal sumCorpus) {
    this.sumCorpus = sumCorpus;
  }
  public BigDecimal getSumInterest() {
    return sumInterest;
  }
  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  
}