package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BankCredence implements Serializable {

  private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer credenceId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String subjectCode;

    /** persistent field */
    private BigDecimal debit;

    /** persistent field */
    private BigDecimal credit;

    /** nullable persistent field */
    private String settType;

    /** nullable persistent field */
    private String settNum;

    /** nullable persistent field */
    private String settDate;

    /** nullable persistent field */
    private String summary;

    /** nullable persistent field */
    private String dopsn;

    /** nullable persistent field */
    private String makebill;

    /** persistent field */
    private String credenceType;

    /** persistent field */
    private String office;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public BankCredence(Integer credenceId, String bookId, String subjectCode, BigDecimal debit, BigDecimal credit, String settType, String settNum, String settDate, String summary, String dopsn, String makebill, String credenceType, String reserveA, String reserveB, String reserveC) {
        this.credenceId = credenceId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.debit = debit;
        this.credit = credit;
        this.settType = settType;
        this.settNum = settNum;
        this.settDate = settDate;
        this.summary = summary;
        this.dopsn = dopsn;
        this.makebill = makebill;
        this.credenceType = credenceType;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public BankCredence() {
    }

    /** minimal constructor */
    public BankCredence(Integer credenceId, String bookId, String subjectCode, BigDecimal debit, BigDecimal credit, String credenceType) {
        this.credenceId = credenceId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.debit = debit;
        this.credit = credit;
        this.credenceType = credenceType;
    }

    public Integer getCredenceId() {
        return this.credenceId;
    }

    public void setCredenceId(Integer credenceId) {
        this.credenceId = credenceId;
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

    public BigDecimal getDebit() {
        return this.debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return this.credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getSettType() {
        return this.settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public String getSettNum() {
        return this.settNum;
    }

    public void setSettNum(String settNum) {
        this.settNum = settNum;
    }

    public String getSettDate() {
        return this.settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDopsn() {
        return this.dopsn;
    }

    public void setDopsn(String dopsn) {
        this.dopsn = dopsn;
    }

    public String getMakebill() {
        return this.makebill;
    }

    public void setMakebill(String makebill) {
        this.makebill = makebill;
    }

    public String getCredenceType() {
        return this.credenceType;
    }

    public void setCredenceType(String credenceType) {
        this.credenceType = credenceType;
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
            .append("credenceId", getCredenceId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BankCredence) ) return false;
        BankCredence castOther = (BankCredence) other;
        return new EqualsBuilder()
            .append(this.getCredenceId(), castOther.getCredenceId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCredenceId())
            .toHashCode();
    }

    public String getOffice() {
      return office;
    }

    public void setOffice(String office) {
      this.office = office;
    }

}
