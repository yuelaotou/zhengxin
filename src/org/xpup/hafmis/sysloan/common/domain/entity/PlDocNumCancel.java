package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PlDocNumCancel implements Serializable {

    /** identifier field */
    private Integer credenceid;

    /** nullable persistent field */
    private String officeCode;

    /** nullable persistent field */
    private String cancelcredenceid;

    /** nullable persistent field */
    private String yearMonth;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public PlDocNumCancel(Integer credenceid, String officeCode, String cancelcredenceid, String yearMonth, String reserveaA, String reserveaB, String reserveaC) {
        this.credenceid = credenceid;
        this.officeCode = officeCode;
        this.cancelcredenceid = cancelcredenceid;
        this.yearMonth = yearMonth;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PlDocNumCancel() {
    }

    /** minimal constructor */
    public PlDocNumCancel(Integer credenceid) {
        this.credenceid = credenceid;
    }

    public Integer getCredenceid() {
        return this.credenceid;
    }

    public void setCredenceid(Integer credenceid) {
        this.credenceid = credenceid;
    }

    public String getOfficeCode() {
        return this.officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getCancelcredenceid() {
        return this.cancelcredenceid;
    }

    public void setCancelcredenceid(String cancelcredenceid) {
        this.cancelcredenceid = cancelcredenceid;
    }

    public String getYearMonth() {
        return this.yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
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
            .append("credenceid", getCredenceid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PlDocNumCancel) ) return false;
        PlDocNumCancel castOther = (PlDocNumCancel) other;
        return new EqualsBuilder()
            .append(this.getCredenceid(), castOther.getCredenceid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCredenceid())
            .toHashCode();
    }

}
