package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class BaseGhgInfo extends DomainObject {

    /** persistent field */
    private String chgColumn;
    private Integer empId;
    /** persistent field */
    private String chgBefInfo;
    private String temp_chgBefInfo;

    /** persistent field */
    private String chgEndInfo;
    private String temp_chgEndInfo;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** nullable persistent field */
    private Emp emp;

    /** nullable persistent field */
    private Org org;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public BaseGhgInfo(String chgColumn, String chgBefInfo, String chgEndInfo, Date opTime, String operator, Emp emp, Org org, String reserveaA, String reserveaB, String reserveaC,Integer empId) {
        this.chgColumn = chgColumn;
        this.chgBefInfo = chgBefInfo;
        this.chgEndInfo = chgEndInfo;
        this.opTime = opTime;
        this.operator = operator;
        this.emp = emp;
        this.org = org;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.empId = empId;
    }

    /** default constructor */
    public BaseGhgInfo() {
    }

    /** minimal constructor */
    public BaseGhgInfo( String chgColumn, String chgBefInfo, String chgEndInfo, Date opTime, String operator) {
        this.chgColumn = chgColumn;
        this.chgBefInfo = chgBefInfo;
        this.chgEndInfo = chgEndInfo;
        this.opTime = opTime;
        this.operator = operator;
    }

    public String getChgColumn() {
        return this.chgColumn;
    }

    public void setChgColumn(String chgColumn) {
        this.chgColumn = chgColumn;
    }

    public String getChgBefInfo() {
        return this.chgBefInfo;
    }

    public void setChgBefInfo(String chgBefInfo) {
        this.chgBefInfo = chgBefInfo;
    }

    public String getChgEndInfo() {
        return this.chgEndInfo;
    }

    public void setChgEndInfo(String chgEndInfo) {
        this.chgEndInfo = chgEndInfo;
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

    public Emp getEmp() {
        return this.emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Org getOrg() {
        return this.org;
    }

    public void setOrg(Org org) {
        this.org = org;
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
        if ( !(other instanceof BaseGhgInfo) ) return false;
        BaseGhgInfo castOther = (BaseGhgInfo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getTemp_chgBefInfo() {
      return temp_chgBefInfo;
    }

    public void setTemp_chgBefInfo(String temp_chgBefInfo) {
      this.temp_chgBefInfo = temp_chgBefInfo;
    }

    public String getTemp_chgEndInfo() {
      return temp_chgEndInfo;
    }

    public void setTemp_chgEndInfo(String temp_chgEndInfo) {
      this.temp_chgEndInfo = temp_chgEndInfo;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

}
