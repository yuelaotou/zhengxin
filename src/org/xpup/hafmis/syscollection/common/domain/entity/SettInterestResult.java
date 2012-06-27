package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class SettInterestResult extends DomainObject {

    /** nullable persistent field */
    private String year;

    /** nullable persistent field */
    private Emp emp = new Emp();
    private Integer empId;

    /** nullable persistent field */
    private BigDecimal befPreBalance=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal befCurBalance=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curInterest=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal endPreBalance=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal endCurBalance=new BigDecimal(0.00);
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    
    private Integer settHeadId ;

    public Integer getSettHeadId() {
      return settHeadId;
    }

    public void setSettHeadId(Integer settHeadId) {
      this.settHeadId = settHeadId;
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

    /** full constructor */
    public SettInterestResult(String year,Integer empId,  BigDecimal befPreBalance, BigDecimal befCurBalance, BigDecimal preInterest, BigDecimal curInterest, BigDecimal endPreBalance, BigDecimal endCurBalance,String reserveaA, String reserveaB, String reserveaC,Integer settHeadId) {
       
        this.year = year;
        this.empId = empId;
        this.befPreBalance = befPreBalance;
        this.befCurBalance = befCurBalance;
        this.preInterest = preInterest;
        this.curInterest = curInterest;
        this.endPreBalance = endPreBalance;
        this.endCurBalance = endCurBalance;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.settHeadId = settHeadId;
    }

    /** default constructor */
    public SettInterestResult() {
    }

    /** minimal constructor */
    public SettInterestResult(Integer id) {
        this.setId(id);
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }

    public BigDecimal getBefPreBalance() {
        return this.befPreBalance;
    }

    public void setBefPreBalance(BigDecimal befPreBalance) {
        this.befPreBalance = befPreBalance;
    }

    public BigDecimal getBefCurBalance() {
        return this.befCurBalance;
    }

    public void setBefCurBalance(BigDecimal befCurBalance) {
        this.befCurBalance = befCurBalance;
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

    public BigDecimal getEndPreBalance() {
        return this.endPreBalance;
    }

    public void setEndPreBalance(BigDecimal endPreBalance) {
        this.endPreBalance = endPreBalance;
    }

    public BigDecimal getEndCurBalance() {
        return this.endCurBalance;
    }

    public void setEndCurBalance(BigDecimal endCurBalance) {
        this.endCurBalance = endCurBalance;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SettInterestResult) ) return false;
        SettInterestResult castOther = (SettInterestResult) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

}
