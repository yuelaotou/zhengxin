package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CredenceModle implements Serializable {

    /** identifier field */
    private Integer modleId;

    /** nullable persistent field */
    private String bookId;

    /** nullable persistent field */
    private String subjectCode;

    /** nullable persistent field */
    private String subjectDirection;

    /** nullable persistent field */
    private String bizType;

    /** nullable persistent field */
    private String moneyType;

    /** nullable persistent field */
    private String summray;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public CredenceModle(Integer modleId, String bookId, String subjectCode, String subjectDirection, String bizType, String moneyType, String summray, String reserveA, String reserveB, String reserveC) {
        this.modleId = modleId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.subjectDirection = subjectDirection;
        this.bizType = bizType;
        this.moneyType = moneyType;
        this.summray = summray;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public CredenceModle() {
    }

    /** minimal constructor */
    public CredenceModle(Integer modleId) {
        this.modleId = modleId;
    }

    public Integer getModleId() {
        return this.modleId;
    }

    public void setModleId(Integer modleId) {
        this.modleId = modleId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getBizType() {
        return this.bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getMoneyType() {
        return this.moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getSummray() {
        return this.summray;
    }

    public void setSummray(String summray) {
        this.summray = summray;
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
            .append("modleId", getModleId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CredenceModle) ) return false;
        CredenceModle castOther = (CredenceModle) other;
        return new EqualsBuilder()
            .append(this.getModleId(), castOther.getModleId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getModleId())
            .toHashCode();
    }

}
