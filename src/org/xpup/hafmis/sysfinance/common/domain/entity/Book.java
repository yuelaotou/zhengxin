package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Book implements Serializable {

    /** identifier field */
    private Integer bookFlowId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String bookName;

    /** nullable persistent field */
    private Date foundTime;

    /** nullable persistent field */
    private String foundPerson;

    /** nullable persistent field */
    private String useYearmonth;

    /** nullable persistent field */
    private String usePerson;

    /** persistent field */
    private String bookSt;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public Book(Integer bookFlowId, String bookId, String bookName, Date foundTime, String foundPerson, String useYearmonth, String usePerson, String bookSt, String reserveA, String reserveB, String reserveC) {
        this.bookFlowId = bookFlowId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.foundTime = foundTime;
        this.foundPerson = foundPerson;
        this.useYearmonth = useYearmonth;
        this.usePerson = usePerson;
        this.bookSt = bookSt;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public Book() {
    }

    /** minimal constructor */
    public Book(Integer bookFlowId, String bookId, String bookName, String bookSt) {
        this.bookFlowId = bookFlowId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookSt = bookSt;
    }

    public Integer getBookFlowId() {
        return this.bookFlowId;
    }

    public void setBookFlowId(Integer bookFlowId) {
        this.bookFlowId = bookFlowId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getFoundTime() {
        return this.foundTime;
    }

    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    public String getFoundPerson() {
        return this.foundPerson;
    }

    public void setFoundPerson(String foundPerson) {
        this.foundPerson = foundPerson;
    }

    public String getUseYearmonth() {
        return this.useYearmonth;
    }

    public void setUseYearmonth(String useYearmonth) {
        this.useYearmonth = useYearmonth;
    }

    public String getUsePerson() {
        return this.usePerson;
    }

    public void setUsePerson(String usePerson) {
        this.usePerson = usePerson;
    }

    public String getBookSt() {
        return this.bookSt;
    }

    public void setBookSt(String bookSt) {
        this.bookSt = bookSt;
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
            .append("bookFlowId", getBookFlowId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Book) ) return false;
        Book castOther = (Book) other;
        return new EqualsBuilder()
            .append(this.getBookFlowId(), castOther.getBookFlowId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBookFlowId())
            .toHashCode();
    }

}
