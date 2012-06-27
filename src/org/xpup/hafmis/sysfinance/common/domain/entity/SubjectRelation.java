package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SubjectRelation implements Serializable {

    /** identifier field */
    private Integer subjectReleId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String subjectCode;

    /** persistent field */
    private String firstSubjectCode;

    /** persistent field */
    private String calculRelaValue;

    /** persistent field */
    private String calculRelaType;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public SubjectRelation(Integer subjectReleId, String bookId, String subjectCode, String firstSubjectCode, String calculRelaValue, String calculRelaType, String reserveA, String reserveB, String reserveC) {
        this.subjectReleId = subjectReleId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.firstSubjectCode = firstSubjectCode;
        this.calculRelaValue = calculRelaValue;
        this.calculRelaType = calculRelaType;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public SubjectRelation() {
    }

    /** minimal constructor */
    public SubjectRelation(Integer subjectReleId, String bookId, String subjectCode, String firstSubjectCode, String calculRelaValue, String calculRelaType) {
        this.subjectReleId = subjectReleId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.firstSubjectCode = firstSubjectCode;
        this.calculRelaValue = calculRelaValue;
        this.calculRelaType = calculRelaType;
    }

    public Integer getSubjectReleId() {
        return this.subjectReleId;
    }

    public void setSubjectReleId(Integer subjectReleId) {
        this.subjectReleId = subjectReleId;
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

    public String getFirstSubjectCode() {
        return this.firstSubjectCode;
    }

    public void setFirstSubjectCode(String firstSubjectCode) {
        this.firstSubjectCode = firstSubjectCode;
    }

    public String getCalculRelaValue() {
        return this.calculRelaValue;
    }

    public void setCalculRelaValue(String calculRelaValue) {
        this.calculRelaValue = calculRelaValue;
    }

    public String getCalculRelaType() {
        return this.calculRelaType;
    }

    public void setCalculRelaType(String calculRelaType) {
        this.calculRelaType = calculRelaType;
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
            .append("subjectReleId", getSubjectReleId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SubjectRelation) ) return false;
        SubjectRelation castOther = (SubjectRelation) other;
        return new EqualsBuilder()
            .append(this.getSubjectReleId(), castOther.getSubjectReleId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSubjectReleId())
            .toHashCode();
    }

}
