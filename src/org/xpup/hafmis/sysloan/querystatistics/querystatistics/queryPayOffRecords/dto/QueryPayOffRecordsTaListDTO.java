package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.dto;

import java.util.List;

//wuht
//列表内容
//合同编号，姓名，贷款金额，贷款期限，利息总额，还款总额，发放日期，到期日期，还清日期。

public class QueryPayOffRecordsTaListDTO {
  


  private String loanKouAcc = "";//贷款帐号

  private String contractId =  "";

  private String borrowerName = "";

  private String loanMoney = "";// 借款金额（万元）（小数点保留一位）

  private String loanTimeLimit = "";// 借款期限

  private String loanStartDate = "";// 发放日期

  private String loanRepayDay =  "";// ，到期日期

  private String loanPayOffDate = "";// ，还清日期。
  

  private String interest = "";//利息总额， 

  private String corpus = "";//，还款总额

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanPayOffDate() {
    return loanPayOffDate;
  }

  public void setLoanPayOffDate(String loanPayOffDate) {
    this.loanPayOffDate = loanPayOffDate;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getCorpus() {
    return corpus;
  }

  public void setCorpus(String corpus) {
    this.corpus = corpus;
  }

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

 
 

}
