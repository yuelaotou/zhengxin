package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanFlowTail implements Serializable {

    /** identifier field */
    private Integer flowTailId;

    /** persistent field */
    private BigDecimal flowHeadId=new BigDecimal(0.00);

    /** nullable persistent field */
    private String loanKouAcc;

    /** nullable persistent field */
    private String yearMonth;

    /** nullable persistent field */
    private BigDecimal shouldCorpus=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal shouldInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal shouldPunishInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal realCorpus=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal realInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal realPunishInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal occurMoney=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal otherInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private String loanType;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** persistent field */
    private String contractId;
    
    private BigDecimal loanRate = new BigDecimal(0.00);
    
    private BigDecimal tempPunishInterest = new BigDecimal(0.00);

    /** full constructor */
    public LoanFlowTail(Integer flowTailId, BigDecimal flowHeadId, String loanKouAcc, String yearMonth, BigDecimal shouldCorpus, BigDecimal shouldInterest, BigDecimal shouldPunishInterest, BigDecimal realCorpus, BigDecimal realInterest, BigDecimal realPunishInterest, BigDecimal occurMoney, BigDecimal otherInterest, String loanType, String reserveaA, String reserveaB, String reserveaC, String contractId,BigDecimal loanRate,BigDecimal tempPunishInterest) {
        this.flowTailId = flowTailId;
        this.flowHeadId = flowHeadId;
        this.loanKouAcc = loanKouAcc;
        this.yearMonth = yearMonth;
        this.shouldCorpus = shouldCorpus;
        this.shouldInterest = shouldInterest;
        this.shouldPunishInterest = shouldPunishInterest;
        this.realCorpus = realCorpus;
        this.realInterest = realInterest;
        this.realPunishInterest = realPunishInterest;
        this.occurMoney = occurMoney;
        this.otherInterest = otherInterest;
        this.loanType = loanType;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.contractId = contractId;
        this.loanRate = loanRate;
        this.tempPunishInterest = tempPunishInterest;
    }

    /** default constructor */
    public LoanFlowTail() {
    }

    /** minimal constructor */
    public LoanFlowTail(Integer flowTailId, BigDecimal flowHeadId, String contractId,BigDecimal shouldCorpus, BigDecimal shouldInterest, BigDecimal shouldPunishInterest, BigDecimal realCorpus, BigDecimal realInterest, BigDecimal realPunishInterest,BigDecimal tempPunishInterest) {
        this.flowTailId = flowTailId;
        this.flowHeadId = flowHeadId;
        this.contractId = contractId;
        this.shouldCorpus = shouldCorpus;
        this.shouldInterest = shouldInterest;
        this.shouldPunishInterest = shouldPunishInterest;
        this.realCorpus = realCorpus;
        this.realInterest = realInterest;
        this.realPunishInterest = realPunishInterest;
        this.tempPunishInterest = tempPunishInterest;
    }

    public Integer getFlowTailId() {
        return this.flowTailId;
    }

    public void setFlowTailId(Integer flowTailId) {
        this.flowTailId = flowTailId;
    }

    public BigDecimal getFlowHeadId() {
        return this.flowHeadId;
    }

    public BigDecimal getLoanRate() {
      return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
      this.loanRate = loanRate;
    }

    public void setFlowHeadId(BigDecimal flowHeadId) {
        this.flowHeadId = flowHeadId;
    }

    public String getLoanKouAcc() {
        return this.loanKouAcc;
    }

    public void setLoanKouAcc(String loanKouAcc) {
        this.loanKouAcc = loanKouAcc;
    }

    public String getYearMonth() {
        return this.yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
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

    public BigDecimal getShouldPunishInterest() {
        return this.shouldPunishInterest;
    }

    public void setShouldPunishInterest(BigDecimal shouldPunishInterest) {
        this.shouldPunishInterest = shouldPunishInterest;
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

    public String getLoanType() {
        return this.loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
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

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getTempPunishInterest() {
      return tempPunishInterest;
    }

    public void setTempPunishInterest(BigDecimal tempPunishInterest) {
      this.tempPunishInterest = tempPunishInterest;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("flowTailId", getFlowTailId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof LoanFlowTail) ) return false;
        LoanFlowTail castOther = (LoanFlowTail) other;
        return new EqualsBuilder()
            .append(this.getFlowTailId(), castOther.getFlowTailId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFlowTailId())
            .toHashCode();
    }

}
