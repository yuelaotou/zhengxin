package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContractNumCancel implements Serializable {

    /** identifier field */
    private Integer flowid;

    /** nullable persistent field */
    private String office;

    /** nullable persistent field */
    private String cancelcontractid;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public ContractNumCancel (Integer flowid, String office, String cancelcontractid, String reserveaA, String reserveaB, String reserveaC) {
        this.flowid = flowid;
        this.office = office;
        this.cancelcontractid = cancelcontractid;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public ContractNumCancel () {
    }

    /** minimal constructor */
    public ContractNumCancel (Integer flowid) {
        this.flowid = flowid;
    }

    public Integer getFlowid() {
        return this.flowid;
    }

    public void setFlowid(Integer flowid) {
        this.flowid = flowid;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCancelcontractid() {
        return this.cancelcontractid;
    }

    public void setCancelcontractid(String cancelcontractid) {
        this.cancelcontractid = cancelcontractid;
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
            .append("flowid", getFlowid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ContractNumCancel) ) return false;
        ContractNumCancel castOther = (ContractNumCancel) other;
        return new EqualsBuilder()
            .append(this.getFlowid(), castOther.getFlowid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFlowid())
            .toHashCode();
    }
}
