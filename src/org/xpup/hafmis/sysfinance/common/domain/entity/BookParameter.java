package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/** @author Hibernate CodeGenerator */
public class BookParameter implements Serializable {

    /** identifier field */
    private Integer paraId;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String paramNum;

    /** persistent field */
    private String paramDescrip;

    /** nullable persistent field */
    private String paramValue;

    /** nullable persistent field */
    private String paramExplain;

    /** nullable persistent field */
    private String paramExplainExplain;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public BookParameter(Integer paraId, String bookId, String paramNum, String paramDescrip, String paramValue, String paramExplain, String paramExplainExplain, String reserveA, String reserveB, String reserveC) {
        this.paraId = paraId;
        this.bookId = bookId;
        this.paramNum = paramNum;
        this.paramDescrip = paramDescrip;
        this.paramValue = paramValue;
        this.paramExplain = paramExplain;
        this.paramExplainExplain = paramExplainExplain;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public BookParameter() {
    }

    /** minimal constructor */
    public BookParameter(Integer paraId, String bookId, String paramNum, String paramDescrip) {
        this.paraId = paraId;
        this.bookId = bookId;
        this.paramNum = paramNum;
        this.paramDescrip = paramDescrip;
    }

    public Integer getParaId() {
        return this.paraId;
    }

    public void setParaId(Integer paraId) {
        this.paraId = paraId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getParamNum() {
        return this.paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum;
    }

    public String getParamDescrip() {
        return this.paramDescrip;
    }

    public void setParamDescrip(String paramDescrip) {
        this.paramDescrip = paramDescrip;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamExplain() {
        return this.paramExplain;
    }

    public void setParamExplain(String paramExplain) {
        this.paramExplain = paramExplain;
    }

    public String getParamExplainExplain() {
        return this.paramExplainExplain;
    }

    public void setParamExplainExplain(String paramExplainExplain) {
        this.paramExplainExplain = paramExplainExplain;
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
            .append("paraId", getParaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BookParameter) ) return false;
        BookParameter castOther = (BookParameter) other;
        return new EqualsBuilder()
            .append(this.getParaId(), castOther.getParaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getParaId())
            .toHashCode();
    }

}
