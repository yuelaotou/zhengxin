package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FnDocNumMaintain implements Serializable {

    /** identifier field */
    private Integer credencenumId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String credenceNum;

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
    public FnDocNumMaintain(Integer credencenumId, String bookId, String credenceNum, String office, String bizYearmonth, String credenceNumType, String reserveA, String reserveB, String reserveC) {
        this.credencenumId = credencenumId;
        this.bookId = bookId;
        this.credenceNum = credenceNum;
        this.office = office;
        this.bizYearmonth = bizYearmonth;
        this.credenceNumType = credenceNumType;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public FnDocNumMaintain() {
    }

    /** minimal constructor */
    public FnDocNumMaintain(Integer credencenumId, String bookId, String credenceNum, String bizYearmonth, String credenceNumType) {
        this.credencenumId = credencenumId;
        this.bookId = bookId;
        this.credenceNum = credenceNum;
        this.bizYearmonth = bizYearmonth;
        this.credenceNumType = credenceNumType;
    }

    public Integer getCredencenumId() {
        return this.credencenumId;
    }

    public void setCredencenumId(Integer credencenumId) {
        this.credencenumId = credencenumId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCredenceNum() {
        return this.credenceNum;
    }

    public void setCredenceNum(String credenceNum) {
        this.credenceNum = credenceNum;
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
            .append("credencenumId", getCredencenumId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof FnDocNumMaintain) ) return false;
        FnDocNumMaintain castOther = (FnDocNumMaintain) other;
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
