package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PlDocNumMaintain implements Serializable {

    /** identifier field */
    private Integer credencenumId;

    /** nullable persistent field */
    private String officeCode;

    /** nullable persistent field */
    private String credenceNum;

    /** nullable persistent field */
    private String yeraMonth;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public PlDocNumMaintain  (Integer credencenumId, String officeCode, String credenceNum, String yeraMonth, String reserveaA, String reserveaB, String reserveaC) {
        this.credencenumId = credencenumId;
        this.officeCode = officeCode;
        this.credenceNum = credenceNum;
        this.yeraMonth = yeraMonth;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PlDocNumMaintain  () {
    }

    /** minimal constructor */
    public PlDocNumMaintain  (Integer credencenumId) {
        this.credencenumId = credencenumId;
    }

    public Integer getCredencenumId() {
        return this.credencenumId;
    }

    public void setCredencenumId(Integer credencenumId) {
        this.credencenumId = credencenumId;
    }

    public String getOfficeCode() {
        return this.officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getCredenceNum() {
        return this.credenceNum;
    }

    public void setCredenceNum(String credenceNum) {
        this.credenceNum = credenceNum;
    }

    public String getYeraMonth() {
        return this.yeraMonth;
    }

    public void setYeraMonth(String yeraMonth) {
        this.yeraMonth = yeraMonth;
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
            .append("credencenumId", getCredencenumId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PlDocNumMaintain  ) ) return false;
        PlDocNumMaintain   castOther = (PlDocNumMaintain  ) other;
        return new EqualsBuilder()
            .append(this.getCredencenumId(), castOther.getCredencenumId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCredencenumId())
            .toHashCode();
    }

}
