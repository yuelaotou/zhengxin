package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanBank implements Serializable {

    /** identifier field */
    private Integer id ;

    /** persistent field */
    private BigDecimal loanBankId;

    /** nullable persistent field */
    private String loanAcc;

    /** nullable persistent field */
    private String interestAcc;

    /** nullable persistent field */
    private String bankPrisident;

    /** nullable persistent field */
    private String bankPrisidentTel;

    /** nullable persistent field */
    private String contactPrsn;

    /** nullable persistent field */
    private String contactTel;

    /** nullable persistent field */
    private String business;

    /** persistent field */
    private String office;

    /** persistent field */
    private String loanBnakSt;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String yearClear;
    
    private String outAccount="";
    
    private String inAccount;

    /** full constructor */
    public LoanBank(Integer id, BigDecimal loanBankId, String loanAcc, String interestAcc, String bankPrisident, String bankPrisidentTel, String contactPrsn, String contactTel, String business, String office, String loanBnakSt, String reserveaA, String reserveaB, String reserveaC,String yearClear) {
        this.id = id;
        this.loanBankId = loanBankId;
        this.loanAcc = loanAcc;
        this.interestAcc = interestAcc;
        this.bankPrisident = bankPrisident;
        this.bankPrisidentTel = bankPrisidentTel;
        this.contactPrsn = contactPrsn;
        this.contactTel = contactTel;
        this.business = business;
        this.office = office;
        this.loanBnakSt = loanBnakSt;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.yearClear = yearClear;
    }

    /** default constructor */
    public LoanBank() {
    }

    /** minimal constructor */
    public LoanBank(Integer id, BigDecimal loanBankId, String office, String loanBnakSt) {
        this.id = id;
        this.loanBankId = loanBankId;
        this.office = office;
        this.loanBnakSt = loanBnakSt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLoanBankId() {
        return this.loanBankId;
    }

    public void setLoanBankId(BigDecimal loanBankId) {
        this.loanBankId = loanBankId;
    }

    public String getLoanAcc() {
        return this.loanAcc;
    }

    public void setLoanAcc(String loanAcc) {
        this.loanAcc = loanAcc;
    }

    public String getInterestAcc() {
        return this.interestAcc;
    }

    public void setInterestAcc(String interestAcc) {
        this.interestAcc = interestAcc;
    }

    public String getBankPrisident() {
        return this.bankPrisident;
    }

    public void setBankPrisident(String bankPrisident) {
        this.bankPrisident = bankPrisident;
    }

    public String getBankPrisidentTel() {
        return this.bankPrisidentTel;
    }

    public void setBankPrisidentTel(String bankPrisidentTel) {
        this.bankPrisidentTel = bankPrisidentTel;
    }

    public String getContactPrsn() {
        return this.contactPrsn;
    }

    public void setContactPrsn(String contactPrsn) {
        this.contactPrsn = contactPrsn;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLoanBnakSt() {
        return this.loanBnakSt;
    }

    public void setLoanBnakSt(String loanBnakSt) {
        this.loanBnakSt = loanBnakSt;
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
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof LoanBank) ) return false;
        LoanBank castOther = (LoanBank) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getYearClear() {
      return yearClear;
    }

    public void setYearClear(String yearClear) {
      this.yearClear = yearClear;
    }

    public String getInAccount() {
      return inAccount;
    }

    public void setInAccount(String inAccount) {
      this.inAccount = inAccount;
    }

    public String getOutAccount() {
      return outAccount;
    }

    public void setOutAccount(String outAccount) {
      this.outAccount = outAccount;
    }

}
