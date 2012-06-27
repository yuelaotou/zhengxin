package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CollLoanbackHead implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String batchNum;

    /** nullable persistent field */
    private String loanBankId;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    private String bizDate;

    public String getBizDate() {
      return bizDate;
    }

    public void setBizDate(String bizDate) {
      this.bizDate = bizDate;
    }

    /** full constructor */
    public CollLoanbackHead(Integer id, String batchNum, String loanBankId, String status, String reserveaA, String reserveaB, String reserveaC) {
        this.id = id;
        this.batchNum = batchNum;
        this.loanBankId = loanBankId;
        this.status = status;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public CollLoanbackHead() {
    }

    /** minimal constructor */
    public CollLoanbackHead(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchNum() {
        return this.batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if ( !(other instanceof CollLoanbackHead) ) return false;
        CollLoanbackHead castOther = (CollLoanbackHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getLoanBankId() {
      return loanBankId;
    }

    public void setLoanBankId(String loanBankId) {
      this.loanBankId = loanBankId;
    }

}

