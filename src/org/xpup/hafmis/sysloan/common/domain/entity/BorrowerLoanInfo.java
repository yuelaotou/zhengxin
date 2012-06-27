package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BorrowerLoanInfo implements Serializable {

    /** identifier field */
    private String contractId;

    /** persistent field */
    private BigDecimal loanMoney;

    /** persistent field */
    private String loanTimeLimit;

    /** persistent field */
    private String loanMode;

    /** persistent field */
    private BigDecimal loanMonthRate;

    /** nullable persistent field */
    private BigDecimal corpusInterest;

    /** persistent field */
    private String loanRateType;

    /** nullable persistent field */
    private BigDecimal loanPoundage;

    /** persistent field */
    private BigDecimal firstLoanMoney;

    /** nullable persistent field */
    private String operator;

    /** nullable persistent field */
    private Date opTime;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    private String photoUrl;

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public BorrowerLoanInfo(String contractId, BigDecimal loanMoney, String loanTimeLimit, String loanMode, BigDecimal loanMonthRate, BigDecimal corpusInterest, String loanRateType, BigDecimal loanPoundage, BigDecimal firstLoanMoney, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC) {
        this.contractId = contractId;
        this.loanMoney = loanMoney;
        this.loanTimeLimit = loanTimeLimit;
        this.loanMode = loanMode;
        this.loanMonthRate = loanMonthRate;
        this.corpusInterest = corpusInterest;
        this.loanRateType = loanRateType;
        this.loanPoundage = loanPoundage;
        this.firstLoanMoney = firstLoanMoney;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public BorrowerLoanInfo() {
    }

    /** minimal constructor */
    public BorrowerLoanInfo(String contractId, BigDecimal loanMoney, String loanTimeLimit, String loanMode, BigDecimal loanMonthRate, String loanRateType,String operator, Date opTime) {
        this.contractId = contractId;
        this.loanMoney = loanMoney;
        this.loanTimeLimit = loanTimeLimit;
        this.loanMode = loanMode;
        this.loanMonthRate = loanMonthRate;
        this.loanRateType = loanRateType;
        this.operator = operator;
        this.opTime = opTime;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getLoanMoney() {
        return this.loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getLoanTimeLimit() {
        return this.loanTimeLimit;
    }

    public void setLoanTimeLimit(String loanTimeLimit) {
        this.loanTimeLimit = loanTimeLimit;
    }

    public String getLoanMode() {
        return this.loanMode;
    }

    public void setLoanMode(String loanMode) {
        this.loanMode = loanMode;
    }

    public BigDecimal getLoanMonthRate() {
        return this.loanMonthRate;
    }

    public void setLoanMonthRate(BigDecimal loanMonthRate) {
        this.loanMonthRate = loanMonthRate;
    }

    public BigDecimal getCorpusInterest() {
        return this.corpusInterest;
    }

    public void setCorpusInterest(BigDecimal corpusInterest) {
        this.corpusInterest = corpusInterest;
    }

    public String getLoanRateType() {
        return this.loanRateType;
    }

    public void setLoanRateType(String loanRateType) {
        this.loanRateType = loanRateType;
    }

    public BigDecimal getLoanPoundage() {
        return this.loanPoundage;
    }

    public void setLoanPoundage(BigDecimal loanPoundage) {
        this.loanPoundage = loanPoundage;
    }

    public BigDecimal getFirstLoanMoney() {
        return this.firstLoanMoney;
    }

    public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
        this.firstLoanMoney = firstLoanMoney;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("contractId", getContractId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BorrowerLoanInfo) ) return false;
        BorrowerLoanInfo castOther = (BorrowerLoanInfo) other;
        return new EqualsBuilder()
            .append(this.getContractId(), castOther.getContractId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContractId())
            .toHashCode();
    }

}
