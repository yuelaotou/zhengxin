package org.xpup.hafmis.syscollection.common.domain.entity;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class OrgChgLog extends DomainObject {

    /** persistent field */
    private Org org;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;
    private String temp_type="";
    /** nullable persistent field */
    private String reserveaC;
    private String chgType;
    /** full constructor */
    public OrgChgLog(Org org, Date opTime, String operator, String reserveaA, String reserveaB, String reserveaC) {
        this.org = org;
        this.opTime = opTime;
        this.operator = operator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public OrgChgLog() {
    }

    public Org  getOrg() {
        return this.org;
    }

    public void setOrg(Org org ) {
        this.org = org;
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
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof OrgChgLog) ) return false;
        OrgChgLog castOther = (OrgChgLog) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
    public String getChgType() {
      return chgType;
    }

    public void setChgType(String chgType) {
      this.chgType = chgType;
    }

    public String getTemp_type() {
      return temp_type;
    }

    public void setTemp_type(String temp_type) {
      this.temp_type = temp_type;
    }

}
