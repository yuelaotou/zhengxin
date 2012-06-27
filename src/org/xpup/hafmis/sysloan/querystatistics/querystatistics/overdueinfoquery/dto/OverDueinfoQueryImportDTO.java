package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto;

public class OverDueinfoQueryImportDTO {
  //银行代码
  private String loanBankId = "";
  //贷款账号
  private String loanKouAcc = "";
  //姓名
  private String borrowerName = "";
  //未还本金
  private String nobackCorpus = "";
  //未还利息
  private String nobackInterest = "";
  //未还罚息
  private String nobackPunishInterest = "";
  //逾期月数
  private String monthsCount = "";
  //月还本息
  private String corpusInterest = "";
  //扣款日期
  private String bizDate = "";

  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getMonthsCount() {
    return monthsCount;
  }
  public void setMonthsCount(String monthsCount) {
    this.monthsCount = monthsCount;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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
 
  public String getNobackPunishInterest() {
    return nobackPunishInterest;
  }
  public void setNobackPunishInterest(String nobackPunishInterest) {
    this.nobackPunishInterest = nobackPunishInterest;
  }  
  
}