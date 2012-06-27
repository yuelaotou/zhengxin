package org.xpup.hafmis.sysfinance.bookmng.subject.dto;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class SubjectDTO implements Serializable {

  private static final long serialVersionUID = 1L;

    /** identifier field */
    private String subjectId;

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

   
    public String getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(String subjectId) {
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


}
