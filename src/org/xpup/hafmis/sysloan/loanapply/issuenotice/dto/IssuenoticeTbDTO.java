package org.xpup.hafmis.sysloan.loanapply.issuenotice.dto;

import java.math.BigDecimal;
import java.util.List;

public class IssuenoticeTbDTO {
  private List list = null;

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKind = "";// 证件类型

  private String temp_cardKind = "";

  private String cardNum = "";// 证件号码

  private String loanBankId = "";// 放款银行

  private BigDecimal loanMoney = new BigDecimal(0.00);// 借款金额

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// 每月利率

  private String loanMode = "";// 还款方式

  private String temp_loanMode = "";

  private String loanTimeLimit = "";// 借款期限
  
  private BigDecimal loanMoneySum=new BigDecimal(0.00);//借款金额
  
  private String contractSt=""; 
  
  private String temp_loanMonthRate="";
  
  private String isPrint="";
  
  private String developerName="";
  
  private String houseAddr = "";// 房屋地址
  
  private String ischecked="";
  

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getTemp_cardKind() {
    return temp_cardKind;
  }

  public void setTemp_cardKind(String temp_cardKind) {
    this.temp_cardKind = temp_cardKind;
  }

  public String getTemp_loanMode() {
    return temp_loanMode;
  }

  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public void reset() {
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.cardKind = "";
    this.loanBankId = "";
    this.loanMoney = null;
    this.loanMonthRate = null;
    this.loanMode = "";
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public BigDecimal getLoanMoneySum() {
    return loanMoneySum;
  }

  public void setLoanMoneySum(BigDecimal loanMoneySum) {
    this.loanMoneySum = loanMoneySum;
  }

  public String getTemp_loanMonthRate() {
    return temp_loanMonthRate;
  }

  public void setTemp_loanMonthRate(String temp_loanMonthRate) {
    this.temp_loanMonthRate = temp_loanMonthRate;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getIsPrint() {
    return isPrint;
  }

  public void setIsPrint(String isPrint) {
    this.isPrint = isPrint;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getIschecked() {
    return ischecked;
  }

  public void setIschecked(String ischecked) {
    this.ischecked = ischecked;
  }
}
