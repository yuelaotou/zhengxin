package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanFlowHead implements Serializable {

  /** identifier field */
  private Integer flowHeadId;

  /** persistent field */
  private String bizDate;

  /** persistent field */
  private String bizType;

  /** nullable persistent field */
  private BigDecimal shouldCount = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal shouldCorpus = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal shouldInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal shouldOverdueInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal shouldPunishInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realCount = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realCorpus = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal realPunishInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal otherInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private String docNum;

  /** nullable persistent field */
  private String noteNum;

  /** nullable persistent field */
  private String bizSt;

  /** nullable persistent field */
  private BigDecimal loanBankId = new BigDecimal(0.00);

  /** nullable persistent field */
  private String hedaiOrg;

  /** nullable persistent field */
  private BigDecimal wrongFlowNum = new BigDecimal(0.00);

  /** nullable persistent field */
  private String makePerson;

  /** nullable persistent field */
  private String checkPerson;

  /** nullable persistent field */
  private String clearAccPerson;

  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;

  private String deadLine;

  private BigDecimal loanPoundageMoney = new BigDecimal(0.00);

  private String wrongBizType;

  private String isAdjust;

  private Integer isFinance;

  // Åú´ÎºÅ
  private String batchNum;

  private Integer aheadCheckId;

  private Integer chgId;

  public Integer getChgId() {
    return chgId;
  }

  public void setChgId(Integer chgId) {
    this.chgId = chgId;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  /** full constructor */
  public LoanFlowHead(Integer flowHeadId, String bizDate, String bizType,
      BigDecimal shouldCount, BigDecimal shouldCorpus,
      BigDecimal shouldInterest, BigDecimal shouldOverdueCorpus,
      BigDecimal shouldOverdueInterest, BigDecimal shouldPunishInterest,
      BigDecimal realCount, BigDecimal realCorpus, BigDecimal realInterest,
      BigDecimal realOverdueCorpus, BigDecimal realOverdueInterest,
      BigDecimal realPunishInterest, BigDecimal occurMoney,
      BigDecimal otherInterest, String docNum, String noteNum, String bizSt,
      BigDecimal loanBankId, String hedaiOrg, BigDecimal wrongFlowNum,
      String makePerson, String checkPerson, String clearAccPerson,
      String reserveaA, String reserveaB, String reserveaC, String deadLine,
      BigDecimal loanPoundageMoney, String wrongBizType, String isAdjust,
      Integer isFinance, String batchNum, Integer aheadCheckId, Integer chgId) {
    this.flowHeadId = flowHeadId;
    this.bizDate = bizDate;
    this.bizType = bizType;
    this.shouldCount = shouldCount;
    this.shouldCorpus = shouldCorpus;
    this.shouldInterest = shouldInterest;
    this.shouldOverdueCorpus = shouldOverdueCorpus;
    this.shouldOverdueInterest = shouldOverdueInterest;
    this.shouldPunishInterest = shouldPunishInterest;
    this.realCount = realCount;
    this.realCorpus = realCorpus;
    this.realInterest = realInterest;
    this.realOverdueCorpus = realOverdueCorpus;
    this.realOverdueInterest = realOverdueInterest;
    this.realPunishInterest = realPunishInterest;
    this.occurMoney = occurMoney;
    this.otherInterest = otherInterest;
    this.docNum = docNum;
    this.noteNum = noteNum;
    this.bizSt = bizSt;
    this.loanBankId = loanBankId;
    this.hedaiOrg = hedaiOrg;
    this.wrongFlowNum = wrongFlowNum;
    this.makePerson = makePerson;
    this.checkPerson = checkPerson;
    this.clearAccPerson = clearAccPerson;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.deadLine = deadLine;
    this.loanPoundageMoney = loanPoundageMoney;
    this.wrongBizType = wrongBizType;
    this.isAdjust = isAdjust;
    this.isFinance = isFinance;
    this.batchNum = batchNum;
    this.aheadCheckId = aheadCheckId;
    this.chgId = chgId;
  }

  /** default constructor */
  public LoanFlowHead() {
  }

  /** minimal constructor */
  public LoanFlowHead(Integer flowHeadId, String bizDate, String bizType,
      Integer isFinance) {
    this.flowHeadId = flowHeadId;
    this.bizDate = bizDate;
    this.bizType = bizType;
    this.isFinance = isFinance;
  }

  public Integer getIsFinance() {
    return isFinance;
  }

  public void setIsFinance(Integer isFinance) {
    this.isFinance = isFinance;
  }

  public Integer getFlowHeadId() {
    return this.flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getBizDate() {
    return this.bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizType() {
    return this.bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getShouldCount() {
    return this.shouldCount;
  }

  public void setShouldCount(BigDecimal shouldCount) {
    this.shouldCount = shouldCount;
  }

  public BigDecimal getShouldCorpus() {
    return this.shouldCorpus;
  }

  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }

  public BigDecimal getShouldInterest() {
    return this.shouldInterest;
  }

  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }

  public BigDecimal getShouldOverdueCorpus() {
    return this.shouldOverdueCorpus;
  }

  public String getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
  }

  public BigDecimal getLoanPoundageMoney() {
    return loanPoundageMoney;
  }

  public void setLoanPoundageMoney(BigDecimal loanPoundageMoney) {
    this.loanPoundageMoney = loanPoundageMoney;
  }

  public void setShouldOverdueCorpus(BigDecimal shouldOverdueCorpus) {
    this.shouldOverdueCorpus = shouldOverdueCorpus;
  }

  public BigDecimal getShouldOverdueInterest() {
    return this.shouldOverdueInterest;
  }

  public void setShouldOverdueInterest(BigDecimal shouldOverdueInterest) {
    this.shouldOverdueInterest = shouldOverdueInterest;
  }

  public BigDecimal getShouldPunishInterest() {
    return this.shouldPunishInterest;
  }

  public void setShouldPunishInterest(BigDecimal shouldPunishInterest) {
    this.shouldPunishInterest = shouldPunishInterest;
  }

  public BigDecimal getRealCount() {
    return this.realCount;
  }

  public void setRealCount(BigDecimal realCount) {
    this.realCount = realCount;
  }

  public BigDecimal getRealCorpus() {
    return this.realCorpus;
  }

  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }

  public BigDecimal getRealInterest() {
    return this.realInterest;
  }

  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }

  public BigDecimal getRealOverdueCorpus() {
    return this.realOverdueCorpus;
  }

  public void setRealOverdueCorpus(BigDecimal realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }

  public BigDecimal getRealOverdueInterest() {
    return this.realOverdueInterest;
  }

  public void setRealOverdueInterest(BigDecimal realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }

  public BigDecimal getRealPunishInterest() {
    return this.realPunishInterest;
  }

  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public BigDecimal getOccurMoney() {
    return this.occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getOtherInterest() {
    return this.otherInterest;
  }

  public void setOtherInterest(BigDecimal otherInterest) {
    this.otherInterest = otherInterest;
  }

  public String getDocNum() {
    return this.docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getNoteNum() {
    return this.noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getBizSt() {
    return this.bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public BigDecimal getLoanBankId() {
    return this.loanBankId;
  }

  public void setLoanBankId(BigDecimal loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getHedaiOrg() {
    return this.hedaiOrg;
  }

  public void setHedaiOrg(String hedaiOrg) {
    this.hedaiOrg = hedaiOrg;
  }

  public BigDecimal getWrongFlowNum() {
    return this.wrongFlowNum;
  }

  public void setWrongFlowNum(BigDecimal wrongFlowNum) {
    this.wrongFlowNum = wrongFlowNum;
  }

  public String getMakePerson() {
    return this.makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public String getCheckPerson() {
    return this.checkPerson;
  }

  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }

  public String getClearAccPerson() {
    return this.clearAccPerson;
  }

  public void setClearAccPerson(String clearAccPerson) {
    this.clearAccPerson = clearAccPerson;
  }

  public String getReserveaA() {
    return this.reserveaA;
  }

  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }

  public String getReserveaB() {
    return this.reserveaB;
  }

  public void setReserveaB(String reserveaB) {
    this.reserveaB = reserveaB;
  }

  public String getReserveaC() {
    return this.reserveaC;
  }

  public void setReserveaC(String reserveaC) {
    this.reserveaC = reserveaC;
  }

  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getIsAdjust() {
    return isAdjust;
  }

  public void setIsAdjust(String isAdjust) {
    this.isAdjust = isAdjust;
  }

  public String toString() {
    return new ToStringBuilder(this).append("flowHeadId", getFlowHeadId())
        .toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof LoanFlowHead))
      return false;
    LoanFlowHead castOther = (LoanFlowHead) other;
    return new EqualsBuilder().append(this.getFlowHeadId(),
        castOther.getFlowHeadId()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getFlowHeadId()).toHashCode();
  }

  public Integer getAheadCheckId() {
    return aheadCheckId;
  }

  public void setAheadCheckId(Integer aheadCheckId) {
    this.aheadCheckId = aheadCheckId;
  }

}
