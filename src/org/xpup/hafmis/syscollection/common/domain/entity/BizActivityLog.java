package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class BizActivityLog extends DomainObject {

    /** persistent field */
    private Integer bizid;

    /** persistent field */
    private Integer action;

    /** persistent field */
    private Date opTime;
    private String types;
    /** persistent field */
    private String operator;

    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;

    public String getReserveaA() {
      return reserveaA;
    }

    public void setReserveaA(String reserveaA) {
      this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
      return reserveaB;
    }

    public void setReserveaB(String reserveaB) {
      this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
      return reserveaC;
    }

    public void setReserveaC(String reserveaC) {
      this.reserveaC = reserveaC;
    }

    /** full constructor */
    public BizActivityLog(Integer bizid, Integer action, Date opTime, String operator, String reserveaA, String reserveaB, String reserveaC) {

        this.bizid = bizid;
        this.action = action;
        this.opTime = opTime;
        this.operator = operator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public BizActivityLog() {
    }

    public Integer getBizid() {
        return this.bizid;
    }

    public void setBizid(Integer bizid) {
        this.bizid = bizid;
    }

    public Integer getAction() {
        return this.action;
    }

    public void setAction(Integer action) {
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BizActivityLog) ) return false;
        BizActivityLog castOther = (BizActivityLog) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getTypes() {
      return types;
    }

    public void setTypes(String types) {
      this.types = types;
    }

}
