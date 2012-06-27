package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AccountantCredence implements Serializable {
  private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer credenceId;

    /** persistent field */
    private String bookId = "";

    /** nullable persistent field */
    private String credenceNum = "";

    /** nullable persistent field */
    private String subjectCode = "";

    /** nullable persistent field */
    private String summay = "";

    /** nullable persistent field */
    private String freeSummay = "";

    /** nullable persistent field */
    private String oldCredenceNum = "";

    /** nullable persistent field */
    private String settNum = "";

    /** nullable persistent field */
    private BigDecimal debit = new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal credit = new BigDecimal(0.00);

    /** nullable persistent field */
    private String credenceDate = "";

    /** nullable persistent field */
    private String credenceCharacter = "";

    /** nullable persistent field */
    private String makebill = "";

    /** nullable persistent field */
    private String checkpsn = "";

    /** nullable persistent field */
    private String accountpsn = "";

    /** nullable persistent field */
    private String clearpsn = "";

    /** nullable persistent field */
    private String accountCharge = "";

    /** nullable persistent field */
    private String office = "";

    /** nullable persistent field */
    private String credenceSt = "";

    /** nullable persistent field */
    private String incDecSt = "";

    /** nullable persistent field */
    private String cashAccSt = "";

    /** nullable persistent field */
    private String bankAccSt = "";

    /** nullable persistent field */
    private String settType = "";

    /** nullable persistent field */
    private String settDate = "";

    /** nullable persistent field */
    private String reserveA = "";

    /** nullable persistent field */
    private String reserveB = "";

    /** nullable persistent field */
    private String reserveC = "";

    /** full constructor */
    public AccountantCredence(Integer credenceId, String bookId, String credenceNum, String subjectCode, String summay, String freeSummay, String oldCredenceNum, String settNum, BigDecimal debit, BigDecimal credit, String credenceDate, String credenceCharacter, String makebill, String checkpsn, String accountpsn, String clearpsn, String accountCharge, String office, String credenceSt, String incDecSt, String cashAccSt, String bankAccSt, String reserveA, String reserveB, String reserveC) {
        this.credenceId = credenceId;
        this.bookId = bookId;
        this.credenceNum = credenceNum;
        this.subjectCode = subjectCode;
        this.summay = summay;
        this.freeSummay = freeSummay;
        this.oldCredenceNum = oldCredenceNum;
        this.settNum = settNum;
        this.debit = debit;
        this.credit = credit;
        this.credenceDate = credenceDate;
        this.credenceCharacter = credenceCharacter;
        this.makebill = makebill;
        this.checkpsn = checkpsn;
        this.accountpsn = accountpsn;
        this.clearpsn = clearpsn;
        this.accountCharge = accountCharge;
        this.office = office;
        this.credenceSt = credenceSt;
        this.incDecSt = incDecSt;
        this.cashAccSt = cashAccSt;
        this.bankAccSt = bankAccSt;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public AccountantCredence() {
    }

    /** minimal constructor */
    public AccountantCredence(Integer credenceId, String bookId) {
        this.credenceId = credenceId;
        this.bookId = bookId;
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

    public String getCredenceNum() {
        return this.credenceNum;
    }

    public void setCredenceNum(String credenceNum) {
        this.credenceNum = credenceNum;
    }

    public String getSubjectCode() {
        return this.subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSummay() {
        return this.summay;
    }

    public void setSummay(String summay) {
        this.summay = summay;
    }

    public String getFreeSummay() {
        return this.freeSummay;
    }

    public void setFreeSummay(String freeSummay) {
        this.freeSummay = freeSummay;
    }

    public String getOldCredenceNum() {
        return this.oldCredenceNum;
    }

    public void setOldCredenceNum(String oldCredenceNum) {
        this.oldCredenceNum = oldCredenceNum;
    }

    public String getSettNum() {
        return this.settNum;
    }

    public void setSettNum(String settNum) {
        this.settNum = settNum;
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

    public String getCredenceDate() {
        return this.credenceDate;
    }

    public void setCredenceDate(String credenceDate) {
        this.credenceDate = credenceDate;
    }

    public String getCredenceCharacter() {
        return this.credenceCharacter;
    }

    public void setCredenceCharacter(String credenceCharacter) {
        this.credenceCharacter = credenceCharacter;
    }

    public String getMakebill() {
        return this.makebill;
    }

    public void setMakebill(String makebill) {
        this.makebill = makebill;
    }

    public String getCheckpsn() {
        return this.checkpsn;
    }

    public void setCheckpsn(String checkpsn) {
        this.checkpsn = checkpsn;
    }

    public String getAccountpsn() {
        return this.accountpsn;
    }

    public void setAccountpsn(String accountpsn) {
        this.accountpsn = accountpsn;
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

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCredenceSt() {
        return this.credenceSt;
    }

    public void setCredenceSt(String credenceSt) {
        this.credenceSt = credenceSt;
    }

    public String getIncDecSt() {
        return this.incDecSt;
    }

    public void setIncDecSt(String incDecSt) {
        this.incDecSt = incDecSt;
    }

    public String getCashAccSt() {
        return this.cashAccSt;
    }

    public void setCashAccSt(String cashAccSt) {
        this.cashAccSt = cashAccSt;
    }

    public String getBankAccSt() {
        return this.bankAccSt;
    }

    public void setBankAccSt(String bankAccSt) {
        this.bankAccSt = bankAccSt;
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
        if ( !(other instanceof AccountantCredence) ) return false;
        AccountantCredence castOther = (AccountantCredence) other;
        return new EqualsBuilder()
            .append(this.getCredenceId(), castOther.getCredenceId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCredenceId())
            .toHashCode();
    }

    public String getSettDate() {
      return settDate;
    }

    public void setSettDate(String settDate) {
      this.settDate = settDate;
    }

    public String getSettType() {
      return settType;
    }

    public void setSettType(String settType) {
      this.settType = settType;
    }

}
