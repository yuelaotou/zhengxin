package org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto;

import java.math.BigDecimal;

public class BailenRolTbDTO {

  private Integer count = new Integer(0);// 记录数

  private BigDecimal occurMoneyTotal = new BigDecimal(0.00);// 合计：保证金金额

  private String contractId = null; // 合同ID

  private String borrowerName = null; // 借款人姓名

  private String cardNum = null; // 证件号码

  private String loanBankName = null; // 放款银行

  private String bizDate = null; // 收取日期

  private String occurMoney = null;// 保证金金额

  private String bizSt = null; // 业务状态

  private String flowHeadId = null;// 贷款流水账 头表ID

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getOccurMoneyTotal() {
    return occurMoneyTotal;
  }

  public void setOccurMoneyTotal(BigDecimal occurMoneyTotal) {
    this.occurMoneyTotal = occurMoneyTotal;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
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

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }

}
