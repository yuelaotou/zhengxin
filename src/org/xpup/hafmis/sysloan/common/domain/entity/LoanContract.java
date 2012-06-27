package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanContract implements Serializable {

    /** identifier field */
    private String contractId;

    /** nullable persistent field */
    private String assurer;

    /** nullable persistent field */
    private String agreementDate;

    /** nullable persistent field */
    private String loanStartDate;

    /** nullable persistent field */
    private String loanPayDate;

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
    
    private String assistantOrgId;
    private String photoUrl;
    private String photoUrl2;

    public String getPhotoUrl2() {
      return photoUrl2;
    }

    public void setPhotoUrl2(String photoUrl2) {
      this.photoUrl2 = photoUrl2;
    }

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public LoanContract(String contractId, String assurer, String agreementDate, String loanStartDate, String loanPayDate, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC,String assistantOrgId) {
        this.contractId = contractId;
        this.assurer = assurer;
        this.agreementDate = agreementDate;
        this.loanStartDate = loanStartDate;
        this.loanPayDate = loanPayDate;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.assistantOrgId = assistantOrgId;
    }

    /** default constructor */
    public LoanContract() {
    }

    /** minimal constructor */
    public LoanContract(String contractId) {
        this.contractId = contractId;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getAssurer() {
        return this.assurer;
    }

    public void setAssurer(String assurer) {
        this.assurer = assurer;
    }

    public String getAgreementDate() {
        return this.agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getLoanStartDate() {
        return this.loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getLoanPayDate() {
        return this.loanPayDate;
    }

    public void setLoanPayDate(String loanPayDate) {
        this.loanPayDate = loanPayDate;
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

    public String getAssistantOrgId() {
      return assistantOrgId;
    }

    public void setAssistantOrgId(String assistantOrgId) {
      this.assistantOrgId = assistantOrgId;
    }

    public boolean equals(Object other) {
        if ( !(other instanceof LoanContract) ) return false;
        LoanContract castOther = (LoanContract) other;
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
