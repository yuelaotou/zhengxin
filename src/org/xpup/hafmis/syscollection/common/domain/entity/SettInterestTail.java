package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class SettInterestTail extends DomainObject {

    /** persistent field */
    private SettInterestHead settInterestHead = new SettInterestHead();

    /** persistent field */
    private Emp emp = new Emp();
    private Integer empId;

    /** nullable persistent field */
    private BigDecimal preIntegral;

    /** nullable persistent field */
    private BigDecimal curIntegral;

    /** nullable persistent field */
    private BigDecimal curRate;

    /** nullable persistent field */
    private BigDecimal preRate;

    /** nullable persistent field */
    private BigDecimal preInterest;

    /** nullable persistent field */
    private BigDecimal curInterest;
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;

    /** full constructor */
    public SettInterestTail(SettInterestHead settInterestHead,Integer empId, BigDecimal settHeadId, BigDecimal preIntegral, BigDecimal curIntegral, BigDecimal curRate, BigDecimal preRate, BigDecimal preInterest, BigDecimal curInterest,String reserveaA, String reserveaB, String reserveaC) {
        this.settInterestHead = settInterestHead;
        this.empId = empId;
        this.preIntegral = preIntegral;
        this.curIntegral = curIntegral;
        this.curRate = curRate;
        this.preRate = preRate;
        this.preInterest = preInterest;
        this.curInterest = curInterest;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public SettInterestTail() {
    }

    /** minimal constructor */
    public SettInterestTail(SettInterestHead settInterestHead,Emp emp) {
      this.settInterestHead = settInterestHead;
      this.emp = emp;
    }

    public BigDecimal getPreIntegral() {
        return this.preIntegral;
    }

    public void setPreIntegral(BigDecimal preIntegral) {
        this.preIntegral = preIntegral;
    }

    public BigDecimal getCurIntegral() {
        return this.curIntegral;
    }

    public void setCurIntegral(BigDecimal curIntegral) {
        this.curIntegral = curIntegral;
    }

    public BigDecimal getCurRate() {
        return this.curRate;
    }

    public void setCurRate(BigDecimal curRate) {
        this.curRate = curRate;
    }

    public BigDecimal getPreRate() {
        return this.preRate;
    }

    public void setPreRate(BigDecimal preRate) {
        this.preRate = preRate;
    }

    public BigDecimal getPreInterest() {
        return this.preInterest;
    }

    public void setPreInterest(BigDecimal preInterest) {
        this.preInterest = preInterest;
    }

    public BigDecimal getCurInterest() {
        return this.curInterest;
    }

    public void setCurInterest(BigDecimal curInterest) {
        this.curInterest = curInterest;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SettInterestTail) ) return false;
        SettInterestTail castOther = (SettInterestTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

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

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }

    public SettInterestHead getSettInterestHead() {
      return settInterestHead;
    }

    public void setSettInterestHead(SettInterestHead settInterestHead) {
      this.settInterestHead = settInterestHead;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

}
