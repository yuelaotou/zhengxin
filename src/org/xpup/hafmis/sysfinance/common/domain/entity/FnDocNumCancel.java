package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FnDocNumCancel implements Serializable {

    /** identifier field */
    private Integer credenceid;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String cancelcredenceid;

    /** nullable persistent field */
    private String office;

    /** persistent field */
    private String bizYearmonth;

    /** persistent field */
    private String credenceNumType;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public FnDocNumCancel(Integer credenceid, String bookId, String cancelcredenceid, String office, String bizYearmonth, String credenceNumType, String reserveA, String reserveB, String reserveC) {
        this.credenceid = credenceid;
        this.bookId = bookId;
        this.cancelcredenceid = cancelcredenceid;
        this.office = office;
        this.bizYearmonth = bizYearmonth;
        this.credenceNumType = credenceNumType;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public FnDocNumCancel() {
    }

    /** minimal constructor */
    public FnDocNumCancel(Integer credenceid, String bookId, String cancelcredenceid, String bizYearmonth, String credenceNumType) {
        this.credenceid = credenceid;
        this.bookId = bookId;
        this.cancelcredenceid = cancelcredenceid;
        this.bizYearmonth = bizYearmonth;
        this.credenceNumType = credenceNumType;
    }

    public Integer getCredenceid() {
        return this.credenceid;
    }

    public void setCredenceid(Integer credenceid) {
        this.credenceid = credenceid;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCancelcredenceid() {
        return this.cancelcredenceid;
    }

    public void setCancelcredenceid(String cancelcredenceid) {
        this.cancelcredenceid = cancelcredenceid;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getBizYearmonth() {
        return this.bizYearmonth;
    }

    public void setBizYearmonth(String bizYearmonth) {
        this.bizYearmonth = bizYearmonth;
    }

    public String getCredenceNumType() {
        return this.credenceNumType;
    }

    public void setCredenceNumType(String credenceNumType) {
        this.credenceNumType = credenceNumType;
    }

    public String getReserveA() {
        return this.reserveA;
    }

    public void setReserveA(String reserveA) {
        this.reserveA = reserveA;
    }

    public String getReserveB() {
        return this.reserveB;
    }

    public void setReserveB(String reserveB) {
        this.reserveB = reserveB;
    }

    public String getReserveC() {
        return this.reserveC;
    }

    public void setReserveC(String reserveC) {
        this.reserveC = reserveC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("credenceid", getCredenceid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof FnDocNumCancel) ) return false;
        FnDocNumCancel castOther = (FnDocNumCancel) other;
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
