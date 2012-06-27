package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * 用来封装被调整信息与调整后信息的DTO
 * 
 * @author 付云峰
 */
public class AdjustAccountTaSaveDTO {

  // PL202
  /**
   * 应还人数
   */
  private BigDecimal shouldCount = new BigDecimal(0.00);

  /**
   * 应还正常本金
   */
  private BigDecimal shouldCorpus = new BigDecimal(0.00);

  /**
   * 应还利息
   */
  private BigDecimal shouldInterest = new BigDecimal(0.00);

  /**
   * 应还逾期本金
   */
  private BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);

  /**
   * 应还逾期利息
   */
  private BigDecimal shouldOverdueInterest = new BigDecimal(0.00);

  /**
   * 应还罚息
   */
  private BigDecimal shouldPunishInterest = new BigDecimal(0.00);

  /**
   * 实还人数
   */
  private BigDecimal realCount = new BigDecimal(0.00);

  /**
   * 实还本金
   */
  private BigDecimal realCorpus = new BigDecimal(0.00);

  /**
   * 实还利息
   */
  private BigDecimal realInterest = new BigDecimal(0.00);

  /**
   * 实还逾期本金
   */
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);

  /**
   * 实还逾期利息
   */
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);

  /**
   * 实还罚息
   */
  private BigDecimal realPunishInterest = new BigDecimal(0.00);

  /**
   * 发生金额
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * 放款银行
   */
  private String loanBankId = "";

  /**
   * 核呆单位
   */
  private String hedaiOrg = "";

  /**
   * 流水号
   */
  private String flowHeadId = "";

  /**
   * 业务类型
   */
  private String bizType = "";

  /**
   * 制单人
   */
  private String makePerson = "";

  // PL203
  /**
   * 合同编号
   */
  private String contractId = "";

  /**
   * 贷款账号
   */
  private String loanKouAcc = "";

  /**
   * 还款年月
   */
  private String yearMonth = "";

  /**
   * 应还本金
   */
  private BigDecimal shouldCorpusTail = new BigDecimal(0.00);

  /**
   * 应还利息
   */
  private BigDecimal shouldInterestTail = new BigDecimal(0.00);

  /**
   * 应还罚息
   */
  private BigDecimal shouldPunishInterestTail = new BigDecimal(0.00);

  /**
   * 实还本金
   */
  private BigDecimal realCorpusTail = new BigDecimal(0.00);

  /**
   * 实还利息
   */
  private BigDecimal realInterestTail = new BigDecimal(0.00);

  /**
   * 实还罚息
   */
  private BigDecimal realPunishInterestTail = new BigDecimal(0.00);

  /**
   * 发生金额
   */
  private BigDecimal occurMoneyTail = new BigDecimal(0.00);
  
  /**
   * 临时利息
   */
  private BigDecimal tempPunishInterest = new BigDecimal(0.00);

  /**
   * 还款类型
   */
  private String loanType = "";

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getHedaiOrg() {
    return hedaiOrg;
  }

  public void setHedaiOrg(String hedaiOrg) {
    this.hedaiOrg = hedaiOrg;
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

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getOccurMoneyTail() {
    return occurMoneyTail;
  }

  public void setOccurMoneyTail(BigDecimal occurMoneyTail) {
    this.occurMoneyTail = occurMoneyTail;
  }

  public BigDecimal getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }

  public BigDecimal getRealCorpusTail() {
    return realCorpusTail;
  }

  public void setRealCorpusTail(BigDecimal realCorpusTail) {
    this.realCorpusTail = realCorpusTail;
  }

  public BigDecimal getRealCount() {
    return realCount;
  }

  public void setRealCount(BigDecimal realCount) {
    this.realCount = realCount;
  }

  public BigDecimal getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }

  public BigDecimal getRealInterestTail() {
    return realInterestTail;
  }

  public void setRealInterestTail(BigDecimal realInterestTail) {
    this.realInterestTail = realInterestTail;
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

  public BigDecimal getRealPunishInterestTail() {
    return realPunishInterestTail;
  }

  public void setRealPunishInterestTail(BigDecimal realPunishInterestTail) {
    this.realPunishInterestTail = realPunishInterestTail;
  }

  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }

  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }

  public BigDecimal getShouldCorpusTail() {
    return shouldCorpusTail;
  }

  public void setShouldCorpusTail(BigDecimal shouldCorpusTail) {
    this.shouldCorpusTail = shouldCorpusTail;
  }

  public BigDecimal getShouldCount() {
    return shouldCount;
  }

  public void setShouldCount(BigDecimal shouldCount) {
    this.shouldCount = shouldCount;
  }

  public BigDecimal getShouldInterest() {
    return shouldInterest;
  }

  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }

  public BigDecimal getShouldInterestTail() {
    return shouldInterestTail;
  }

  public void setShouldInterestTail(BigDecimal shouldInterestTail) {
    this.shouldInterestTail = shouldInterestTail;
  }

  public BigDecimal getShouldOverdueCorpus() {
    return shouldOverdueCorpus;
  }

  public void setShouldOverdueCorpus(BigDecimal shouldOverdueCorpus) {
    this.shouldOverdueCorpus = shouldOverdueCorpus;
  }

  public BigDecimal getShouldOverdueInterest() {
    return shouldOverdueInterest;
  }

  public void setShouldOverdueInterest(BigDecimal shouldOverdueInterest) {
    this.shouldOverdueInterest = shouldOverdueInterest;
  }

  public BigDecimal getShouldPunishInterest() {
    return shouldPunishInterest;
  }

  public void setShouldPunishInterest(BigDecimal shouldPunishInterest) {
    this.shouldPunishInterest = shouldPunishInterest;
  }

  public BigDecimal getShouldPunishInterestTail() {
    return shouldPunishInterestTail;
  }

  public void setShouldPunishInterestTail(BigDecimal shouldPunishInterestTail) {
    this.shouldPunishInterestTail = shouldPunishInterestTail;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public BigDecimal getTempPunishInterest() {
    return tempPunishInterest;
  }

  public void setTempPunishInterest(BigDecimal tempPunishInterest) {
    this.tempPunishInterest = tempPunishInterest;
  }

}
