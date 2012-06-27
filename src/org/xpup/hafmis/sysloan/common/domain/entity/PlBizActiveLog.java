package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PlBizActiveLog implements Serializable {

    /** identifier field */
    private Integer bizactivityLogId;

    /** nullable persistent field */
    private BigDecimal bizid;

    /** persistent field */
    private String action;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** persistent field */
    private String type;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** persistent field */
    private String reserveaC;

    /** full constructor */
    public PlBizActiveLog(Integer bizactivityLogId, BigDecimal bizid, String action, Date opTime, String operator, String type, String reserveaA, String reserveaB, String reserveaC) {
        this.bizactivityLogId = bizactivityLogId;
        this.bizid = bizid;
        this.action = action;
        this.opTime = opTime;
        this.operator = operator;
        this.type = type;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PlBizActiveLog() {
    }

    /** minimal constructor */
    public PlBizActiveLog(Integer bizactivityLogId, String action, Date opTime, String operator, String type, String reserveaC) {
        this.bizactivityLogId = bizactivityLogId;
        this.action = action;
        this.opTime = opTime;
        this.operator = operator;
        this.type = type;
        this.reserveaC = reserveaC;
    }

    public Integer getBizactivityLogId() {
        return this.bizactivityLogId;
    }

    public void setBizactivityLogId(Integer bizactivityLogId) {
        this.bizactivityLogId = bizactivityLogId;
    }

    public BigDecimal getBizid() {
        return this.bizid;
    }

    public void setBizid(BigDecimal bizid) {
        this.bizid = bizid;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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
        if ( !(other instanceof PlBizActiveLog) ) return false;
        PlBizActiveLog castOther = (PlBizActiveLog) other;
        return new EqualsBuilder()
            .append(this.getBizactivityLogId(), castOther.getBizactivityLogId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBizactivityLogId())
            .toHashCode();
    }

}
