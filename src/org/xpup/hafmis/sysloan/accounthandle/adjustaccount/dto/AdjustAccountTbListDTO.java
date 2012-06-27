package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * 封装了错帐维护列表内容的DTO
 * 
 * @author 付云峰
 */
public class AdjustAccountTbListDTO {

  /**
   * 凭证编号
   */
  private String docNum = "";

  /**
   * 合同编号
   */
  private String contractId = "";

  /**
   * 但款帐号
   */
  private String loanKouAcc = "";

  /**
   * 借款人姓名
   */
  private String borrowerName = "";

  /**
   * 业务类型
   */
  private String bizType = "";

  /**
   * 用于显示的中文业务类型
   */
  private String bizTypeStr = "";

  /**
   * 发放金额
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * 回收本金
   */
  private BigDecimal callbackCorpus = new BigDecimal(0.00);

  /**
   * 回收利息
   */
  private BigDecimal callbackInterest = new BigDecimal(0.00);

  /**
   * 罚息利息
   */
  private BigDecimal punishInterest = new BigDecimal(0.00);
  
  /**
   * 实还总额
   */
  private BigDecimal callbackTotal = new BigDecimal(0.00);

  /**
   * 业务状态
   */
  private String bisSt = "";
  
  /**
   * 原流水号
   */
  private String wrongFlowNum = "";

  /**
   * PL202头表id
   */
  private String flowHeadId = "";

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getBisSt() {
    return bisSt;
  }

  public void setBisSt(String bisSt) {
    this.bisSt = bisSt;
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

  public BigDecimal getCallbackCorpus() {
    return callbackCorpus;
  }

  public void setCallbackCorpus(BigDecimal callbackCorpus) {
    this.callbackCorpus = callbackCorpus;
  }

  public BigDecimal getCallbackInterest() {
    return callbackInterest;
  }

  public void setCallbackInterest(BigDecimal callbackInterest) {
    this.callbackInterest = callbackInterest;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getBizTypeStr() {
    return bizTypeStr;
  }

  public void setBizTypeStr(String bizTypeStr) {
    this.bizTypeStr = bizTypeStr;
  }

  public String getWrongFlowNum() {
    return wrongFlowNum;
  }

  public void setWrongFlowNum(String wrongFlowNum) {
    this.wrongFlowNum = wrongFlowNum;
  }

  public BigDecimal getCallbackTotal() {
    return callbackTotal;
  }

  public void setCallbackTotal(BigDecimal callbackTotal) {
    this.callbackTotal = callbackTotal;
  }
}
