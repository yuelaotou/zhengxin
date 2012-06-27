package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;

public class LoancallbackTbDTO {
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
  //本次实还正常本金
  private BigDecimal realCorpus = new BigDecimal(0.00);
  //本次实还正常利息
  private BigDecimal realInterest = new BigDecimal(0.00);
  //本次实还逾期本金
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);
  //本次实还逾期利息
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);
  //实还罚息
  private BigDecimal realPunishInterest = new BigDecimal(0.00);
  //发生额
  private BigDecimal occurMoney = new BigDecimal(0.00);
  //业务类型
  private String bizType = "";
  //业务状态
  private String bizSt = "";
  //头表ID
  private String id = "";
  //实还金额
  private BigDecimal realbackMoney = new BigDecimal(0.00);
  //实收金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  //弹出窗口时所用的业务类型
  private String type = "";
  //业务日期
  private String bizDate = "";
  //是否公积金还贷
  private String yesOrNo = "";
  
  //实还人数
  private String realCount = "";
  
  private String mark="";
  
  public String getRealCount() {
    return realCount;
  }
  public void setRealCount(String realCount) {
    this.realCount = realCount;
  }
  public String getYesOrNo() {
    return yesOrNo;
  }
  public void setYesOrNo(String yesOrNo) {
    this.yesOrNo = yesOrNo;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public BigDecimal getRealbackMoney() {
    return realbackMoney;
  }
  public void setRealbackMoney(BigDecimal realbackMoney) {
    this.realbackMoney = realbackMoney;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }
  public String getBizSt() {
    return bizSt;
  }
  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
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
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
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
  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getMark() {
    return mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }

  
 
  
}