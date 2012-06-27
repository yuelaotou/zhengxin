package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Subject implements Serializable {

    /** identifier field */
    private Integer subjectId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String subjectCode;

    /** persistent field */
    private String subjectName;

    /** nullable persistent field */
    private String parentSubjectCode;

    /** nullable persistent field */
    private String subjectLevel;

    /** persistent field */
    private String subjectSortCode;

    /** persistent field */
    private String balanceDirection;

    /** persistent field */
    private String subjectProperty;

    /** persistent field */
    private String subjectSt;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;
    
    private String bizDate;
    
    private String expireDate;

    /** full constructor */
    public Subject(Integer subjectId, String bookId, String subjectCode, String subjectName, String parentSubjectCode, String subjectLevel, String subjectSortCode, String balanceDirection, String subjectProperty, String subjectSt, String reserveA, String reserveB, String reserveC) {
        this.subjectId = subjectId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.parentSubjectCode = parentSubjectCode;
        this.subjectLevel = subjectLevel;
        this.subjectSortCode = subjectSortCode;
        this.balanceDirection = balanceDirection;
        this.subjectProperty = subjectProperty;
        this.subjectSt = subjectSt;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public Subject() {
    }

    /** minimal constructor */
    public Subject(Integer subjectId, String bookId, String subjectCode, String subjectName, String subjectSortCode, String balanceDirection, String subjectProperty, String subjectSt) {
        this.subjectId = subjectId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectSortCode = subjectSortCode;
        this.balanceDirection = balanceDirection;
        this.subjectProperty = subjectProperty;
        this.subjectSt = subjectSt;
    }

    public Integer getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getParentSubjectCode() {
        return this.parentSubjectCode;
    }

    public void setParentSubjectCode(String parentSubjectCode) {
        this.parentSubjectCode = parentSubjectCode;
    }

    public String getSubjectLevel() {
        return this.subjectLevel;
    }

    public void setSubjectLevel(String subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    public String getSubjectSortCode() {
        return this.subjectSortCode;
    }

    public void setSubjectSortCode(String subjectSortCode) {
        this.subjectSortCode = subjectSortCode;
    }

    public String getBalanceDirection() {
        return this.balanceDirection;
    }

    public void setBalanceDirection(String balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public String getSubjectProperty() {
        return this.subjectProperty;
    }

    public void setSubjectProperty(String subjectProperty) {
        this.subjectProperty = subjectProperty;
    }

    public String getSubjectSt() {
        return this.subjectSt;
    }

    public void setSubjectSt(String subjectSt) {
        this.subjectSt = subjectSt;
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
            .append("subjectId", getSubjectId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Subject) ) return false;
        Subject castOther = (Subject) other;
        return new EqualsBuilder()
            .append(this.getSubjectId(), castOther.getSubjectId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSubjectId())
            .toHashCode();
    }

    public String getBizDate() {
      return bizDate;
    }

    public void setBizDate(String bizDate) {
      this.bizDate = bizDate;
    }

    public String getExpireDate() {
      return expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

}
