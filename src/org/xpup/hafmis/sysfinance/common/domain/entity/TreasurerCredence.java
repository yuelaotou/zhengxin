package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TreasurerCredence implements Serializable {

    /** identifier field */
    private Integer credenceId;

    /** persistent field */
    private String bookId;

    /** nullable persistent field */
    private String acredenceId;

    /** persistent field */
    private String subjectCode;

    /** persistent field */
    private BigDecimal debit;

    /** persistent field */
    private BigDecimal credit;

    /** nullable persistent field */
    private String credenceNum;

    /** nullable persistent field */
    private String settType;

    /** nullable persistent field */
    private String settNum;

    /** nullable persistent field */
    private String summray;

    /** nullable persistent field */
    private String credenceCharacter;

    /** nullable persistent field */
    private String oldCredenceNum;

    /** persistent field */
    private String office;

    /** nullable persistent field */
    private String credenceType;

    /** nullable persistent field */
    private String credenceDate;

    /** nullable persistent field */
    private String settDate;

    /** nullable persistent field */
    private String dopsn;

    /** nullable persistent field */
    private String makebill;

    /** nullable persistent field */
    private String clearpsn;

    /** nullable persistent field */
    private String accountCharge;

    /** nullable persistent field */
    private String credenceSt;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public TreasurerCredence(Integer credenceId, String bookId, String subjectCode, BigDecimal debit, BigDecimal credit, String credenceNum, String settType, String settNum, String summray, String credenceCharacter, String oldCredenceNum, String office, String credenceType, String credenceDate, String settDate, String dopsn, String makebill, String clearpsn, String accountCharge, String credenceSt, String reserveA, String reserveB, String reserveC) {
        this.credenceId = credenceId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.debit = debit;
        this.credit = credit;
        this.credenceNum = credenceNum;
        this.settType = settType;
        this.settNum = settNum;
        this.summray = summray;
        this.credenceCharacter = credenceCharacter;
        this.oldCredenceNum = oldCredenceNum;
        this.office = office;
        this.credenceType = credenceType;
        this.credenceDate = credenceDate;
        this.settDate = settDate;
        this.dopsn = dopsn;
        this.makebill = makebill;
        this.clearpsn = clearpsn;
        this.accountCharge = accountCharge;
        this.credenceSt = credenceSt;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public TreasurerCredence() {
    }

    /** minimal constructor */
    public TreasurerCredence(Integer credenceId, String bookId, String subjectCode, BigDecimal debit, BigDecimal credit, String office) {
        this.credenceId = credenceId;
        this.bookId = bookId;
        this.subjectCode = subjectCode;
        this.debit = debit;
        this.credit = credit;
        this.office = office;
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

    public String getCredenceNum() {
        return this.credenceNum;
    }

    public void setCredenceNum(String credenceNum) {
        this.credenceNum = credenceNum;
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

    public String getSummray() {
        return this.summray;
    }

    public void setSummray(String summray) {
        this.summray = summray;
    }

    public String getCredenceCharacter() {
        return this.credenceCharacter;
    }

    public void setCredenceCharacter(String credenceCharacter) {
        this.credenceCharacter = credenceCharacter;
    }

    public String getOldCredenceNum() {
        return this.oldCredenceNum;
    }

    public void setOldCredenceNum(String oldCredenceNum) {
        this.oldCredenceNum = oldCredenceNum;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCredenceType() {
        return this.credenceType;
    }

    public void setCredenceType(String credenceType) {
        this.credenceType = credenceType;
    }

    public String getCredenceDate() {
        return this.credenceDate;
    }

    public void setCredenceDate(String credenceDate) {
        this.credenceDate = credenceDate;
    }

    public String getSettDate() {
        return this.settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
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

    public String getClearpsn() {
        return this.clearpsn;
    }

    public void setClearpsn(String clearpsn) {
        this.clearpsn = clearpsn;
    }

    public String getAccountCharge() {
        return this.accountCharge;
    }

    public void setAccountCharge(String accountCharge) {
        this.accountCharge = accountCharge;
    }

    public String getCredenceSt() {
        return this.credenceSt;
    }

    public void setCredenceSt(String credenceSt) {
        this.credenceSt = credenceSt;
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
        if ( !(other instanceof TreasurerCredence) ) return false;
        TreasurerCredence castOther = (TreasurerCredence) other;
        return new EqualsBuilder()
            .append(this.getCredenceId(), castOther.getCredenceId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCredenceId())
            .toHashCode();
    }

    public String getAcredenceId() {
      return acredenceId;
    }

    public void setAcredenceId(String acredenceId) {
      this.acredenceId = acredenceId;
    }

}
