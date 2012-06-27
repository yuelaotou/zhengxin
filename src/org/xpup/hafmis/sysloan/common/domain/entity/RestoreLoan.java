package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RestoreLoan implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String contractId;

    /** nullable persistent field */
    private String loanKouYearmonth;

    /** nullable persistent field */
    private BigDecimal shouldCorpus;

    /** nullable persistent field */
    private BigDecimal shouldInterest;

    /** nullable persistent field */
    private BigDecimal realCorpus;

    /** nullable persistent field */
    private BigDecimal realInterest;

    /** nullable persistent field */
    private BigDecimal punishInterest;

    /** nullable persistent field */
    private BigDecimal loanBankId;

    /** nullable persistent field */
    private BigDecimal loanRate;

    /** nullable persistent field */
    private BigDecimal days;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String bizDate;

    /** full constructor */
    public RestoreLoan(Integer id, String contractId, String loanKouYearmonth, BigDecimal shouldCorpus, BigDecimal shouldInterest, BigDecimal realCorpus, BigDecimal realInterest, BigDecimal punishInterest, BigDecimal loanBankId, BigDecimal loanRate, BigDecimal days, String reserveaA, String reserveaB, String reserveaC,String bizDate) {
        this.id = id;
        this.contractId = contractId;
        this.loanKouYearmonth = loanKouYearmonth;
        this.shouldCorpus = shouldCorpus;
        this.shouldInterest = shouldInterest;
        this.realCorpus = realCorpus;
        this.realInterest = realInterest;
        this.punishInterest = punishInterest;
        this.loanBankId = loanBankId;
        this.loanRate = loanRate;
        this.days = days;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.bizDate = bizDate;
    }

    /** default constructor */
    public RestoreLoan() {
    }

    /** minimal constructor */
    public RestoreLoan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getLoanKouYearmonth() {
        return this.loanKouYearmonth;
    }

    public void setLoanKouYearmonth(String loanKouYearmonth) {
        this.loanKouYearmonth = loanKouYearmonth;
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

    public BigDecimal getPunishInterest() {
        return this.punishInterest;
    }

    public void setPunishInterest(BigDecimal punishInterest) {
        this.punishInterest = punishInterest;
    }

    public BigDecimal getLoanBankId() {
        return this.loanBankId;
    }

    public void setLoanBankId(BigDecimal loanBankId) {
        this.loanBankId = loanBankId;
    }

    public BigDecimal getLoanRate() {
        return this.loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getDays() {
        return this.days;
    }

    public void setDays(BigDecimal days) {
        this.days = days;
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

    public String getBizDate() {
      return bizDate;
    }

    public void setBizDate(String bizDate) {
      this.bizDate = bizDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RestoreLoan) ) return false;
        RestoreLoan castOther = (RestoreLoan) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
