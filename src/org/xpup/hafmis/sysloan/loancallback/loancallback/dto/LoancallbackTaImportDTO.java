package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

public class LoancallbackTaImportDTO {
  //银行代码
  private String loanBankId = "";
  //业务类型
  private String bizType = "";
  //核销单位
  private String orgId = "";
  //贷款账号
  private String loanKouAcc = "";
  //姓名
  private String borrowerName = "";
  //还款年月
  private String yearMonth = "";
  //实扣正常本金
  private String realCorpus = "";
  //实扣逾期本金
  private String realOverdueCorpus = "";
  //实扣正常利息
  private String realInterest = "";
  //实扣逾期利息
  private String realOverdueInterest = "";
  //实扣罚息
  private String realPunishInterest = "";
  //未还正常本金
  private String nobackCorpus = "";
  //未还逾期本金
  private String nobackOverdueCorpus = "";
  //未还正常利息
  private String nobackInterest = "";
  //未还逾期利息
  private String nobackOverdueInterest = "";
  //未还罚息
  private String nobackPunishInterest = "";
  //提前还款后剩余期限
  private String deadLine = "";
  //业务日期
  private String bizDate = "";
  //批次号
  private String batchNum = "";
  
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getBizType() {
    return bizType;
  }
  public void setBizType(String bizType) {
    this.bizType = bizType;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getDeadLine() {
    return deadLine;
  }
  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
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
  
  public String getYearMonth() {
    return yearMonth;
  }
  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }
  public String getNobackCorpus() {
    return nobackCorpus;
  }
  public void setNobackCorpus(String nobackCorpus) {
    this.nobackCorpus = nobackCorpus;
  }
  public String getNobackInterest() {
    return nobackInterest;
  }
  public void setNobackInterest(String nobackInterest) {
    this.nobackInterest = nobackInterest;
  }
  public String getNobackOverdueCorpus() {
    return nobackOverdueCorpus;
  }
  public void setNobackOverdueCorpus(String nobackOverdueCorpus) {
    this.nobackOverdueCorpus = nobackOverdueCorpus;
  }
  public String getNobackOverdueInterest() {
    return nobackOverdueInterest;
  }
  public void setNobackOverdueInterest(String nobackOverdueInterest) {
    this.nobackOverdueInterest = nobackOverdueInterest;
  }
  public String getNobackPunishInterest() {
    return nobackPunishInterest;
  }
  public void setNobackPunishInterest(String nobackPunishInterest) {
    this.nobackPunishInterest = nobackPunishInterest;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getRealCorpus() {
    return realCorpus;
  }
  public void setRealCorpus(String realCorpus) {
    this.realCorpus = realCorpus;
  }
  public String getRealInterest() {
    return realInterest;
  }
  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }
  public String getRealOverdueCorpus() {
    return realOverdueCorpus;
  }
  public void setRealOverdueCorpus(String realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }
  public String getRealOverdueInterest() {
    return realOverdueInterest;
  }
  public void setRealOverdueInterest(String realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }
  public String getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(String realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }
  public String getBatchNum() {
    return batchNum;
  }
  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }
  
  
  
}