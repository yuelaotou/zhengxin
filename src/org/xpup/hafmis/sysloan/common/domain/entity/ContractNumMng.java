package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContractNumMng implements Serializable {

    /** identifier field */
    private Integer flownumId;

    /** nullable persistent field */
    private String office;

    /** nullable persistent field */
    private String flowNum;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public ContractNumMng(Integer flownumId, String office, String flowNum, String reserveaA, String reserveaB, String reserveaC) {
        this.flownumId = flownumId;
        this.office = office;
        this.flowNum = flowNum;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public ContractNumMng() {
    }

    /** minimal constructor */
    public ContractNumMng(Integer flownumId) {
        this.flownumId = flownumId;
    }

    public Integer getFlownumId() {
        return this.flownumId;
    }

    public void setFlownumId(Integer flownumId) {
        this.flownumId = flownumId;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFlowNum() {
        return this.flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
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
            .append("flownumId", getFlownumId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ContractNumMng) ) return false;
        ContractNumMng castOther = (ContractNumMng) other;
        return new EqualsBuilder()
            .append(this.getFlownumId(), castOther.getFlownumId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFlownumId())
            .toHashCode();
    }

}
