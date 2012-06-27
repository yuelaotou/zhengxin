package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto;

import java.math.BigDecimal;

public class BadDebtDestroyTbDTO {
  
  //姓名
  private String borrowerName = "";
  
  //证件号码
  private String cardNum = "";
  
  //合同编号
  private String contractId = "";
  
  //贷款账号
  private String loanKouAcc = "";
  
  //凭证号
  private String docNum = "";
  
  //本次总还款本金
  private BigDecimal realCorpus = new BigDecimal(0.00);
  
  //本次总还款利息
  private BigDecimal realInterest = new BigDecimal(0.00);

  //本次总还款金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  
  //业务状态
  private String bizSt = "";
  
  //头表ID
  private String id = "";

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

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
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

  public BigDecimal getRealMoney() {
    return realMoney;
  }

  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }
  
  
}