package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FnBizActivityLog implements Serializable {
    private String bookId;
    /** identifier field */
    private Integer bizactivityLogId;

    /** nullable persistent field */
    private String credenceNum;

    /** nullable persistent field */
    private String credenceType;

    /** persistent field */
    private String credenceDate;

    /** persistent field */
    private String office;

    /** persistent field */
    private String action;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public FnBizActivityLog(String bookId,Integer bizactivityLogId, String credenceNum, String credenceType, String action, Date opTime, String operator, String reserveaA, String reserveaB, String reserveaC) {
        this.bookId=bookId;
        this.bizactivityLogId = bizactivityLogId;
        this.credenceNum = credenceNum;
        this.credenceType = credenceType;
        this.action = action;
        this.opTime = opTime;
        this.operator = operator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public FnBizActivityLog() {
    }

    /** minimal constructor */
    public FnBizActivityLog(Integer bizactivityLogId, String action, Date opTime, String operator) {
        this.bizactivityLogId = bizactivityLogId;
        this.action = action;
        this.opTime = opTime;
        this.operator = operator;
    }

    public String getBookId() {
      return bookId;
    }

    public void setBookId(String bookId) {
      this.bookId = bookId;
    }

    public Integer getBizactivityLogId() {
        return this.bizactivityLogId;
    }

    public void setBizactivityLogId(Integer bizactivityLogId) {
        this.bizactivityLogId = bizactivityLogId;
    }

    public String getCredenceNum() {
        return this.credenceNum;
    }

    public void setCredenceNum(String credenceNum) {
        this.credenceNum = credenceNum;
    }

    public String getCredenceType() {
        return this.credenceType;
    }

    public void setCredenceType(String credenceType) {
        this.credenceType = credenceType;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getReserveaA() {
        return this.reserveaA;
    }

    public void setReserveaA(String reserveaA) {
        this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
        return this.reserveaB;
    }

    public void setReserveaB(String reserveaB) {
        this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
        return this.reserveaC;
    }

    public void setReserveaC(String reserveaC) {
        this.reserveaC = reserveaC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("bizactivityLogId", getBizactivityLogId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof FnBizActivityLog) ) return false;
        FnBizActivityLog castOther = (FnBizActivityLog) other;
        return new EqualsBuilder()
            .append(this.getBizactivityLogId(), castOther.getBizactivityLogId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBizactivityLogId())
            .toHashCode();
    }

    public String getCredenceDate() {
      return credenceDate;
    }

    public void setCredenceDate(String credenceDate) {
      this.credenceDate = credenceDate;
    }

    public String getOffice() {
      return office;
    }

    public void setOffice(String office) {
      this.office = office;
    }

}
