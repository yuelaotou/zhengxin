package org.xpup.hafmis.sysloan.loancallback.bankexports.form;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class BankExportsTaAF extends CriterionsAF {
  
  private static final long serialVersionUID = 157830469042818336L;

  // 合同编号
  private String contractId = "";
  
  // 姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";
      
  //贷款账号
  private String loanKouAcc = "";
  
  //放款银行
  private String loanBankId ="";
  
  //还至年月
  private String day = "";
  
  //还至日
  private String monthYear = "";
  
  //批次号
  private String batchNum = "";
  
  private String temp_day = "";
  
  private String bankId = "";
  
  private String fund_st="";
  
  Map dayMap = new HashMap();
  
  public Map getDayMap() {
    return dayMap;
  }

  public void setDayMap(Map dayMap) {
    this.dayMap = dayMap;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

 
  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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


  public void reset(ActionMapping mapping, ServletRequest request) {
    this.loanBankId="";
    this.borrowerName = "";
    this.cardNum = "";
    this.contractId = "";
    this.loanKouAcc = "";
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getTemp_day() {
    return temp_day;
  }

  public void setTemp_day(String temp_day) {
    this.temp_day = temp_day;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getFund_st() {
    return fund_st;
  }

  public void setFund_st(String fund_st) {
    this.fund_st = fund_st;
  }

}