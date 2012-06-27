package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SettleIncAndDec implements Serializable {

    /** identifier field */
    private Integer incDecId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String bySubjectCode;

    /** persistent field */
    private String bySubjectDirection;

    /** persistent field */
    private String subjectCode;

    /** persistent field */
    private String subjectDirection;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public SettleIncAndDec(Integer incDecId, String bookId, String bySubjectCode, String bySubjectDirection, String subjectCode, String subjectDirection, String reserveA, String reserveB, String reserveC) {
        this.incDecId = incDecId;
        this.bookId = bookId;
        this.bySubjectCode = bySubjectCode;
        this.bySubjectDirection = bySubjectDirection;
        this.subjectCode = subjectCode;
        this.subjectDirection = subjectDirection;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public SettleIncAndDec() {
    }

    /** minimal constructor */
    public SettleIncAndDec(Integer incDecId, String bookId, String bySubjectCode, String bySubjectDirection, String subjectCode, String subjectDirection) {
        this.incDecId = incDecId;
        this.bookId = bookId;
        this.bySubjectCode = bySubjectCode;
        this.bySubjectDirection = bySubjectDirection;
        this.subjectCode = subjectCode;
        this.subjectDirection = subjectDirection;
    }

    public Integer getIncDecId() {
        return this.incDecId;
    }

    public void setIncDecId(Integer incDecId) {
        this.incDecId = incDecId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBySubjectCode() {
        return this.bySubjectCode;
    }

    public void setBySubjectCode(String bySubjectCode) {
        this.bySubjectCode = bySubjectCode;
    }

    public String getBySubjectDirection() {
        return this.bySubjectDirection;
    }

    public void setBySubjectDirection(String bySubjectDirection) {
        this.bySubjectDirection = bySubjectDirection;
    }

    public String getSubjectCode() {
        return this.subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDirection() {
        return this.subjectDirection;
    }

    public void setSubjectDirection(String subjectDirection) {
        this.subjectDirection = subjectDirection;
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
            .append("incDecId", getIncDecId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SettleIncAndDec) ) return false;
        SettleIncAndDec castOther = (SettleIncAndDec) other;
        return new EqualsBuilder()
            .append(this.getIncDecId(), castOther.getIncDecId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIncDecId())
            .toHashCode();
    }

}
