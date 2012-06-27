package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class ChgOrgRateTail extends DomainObject implements Serializable {

  private static final long serialVersionUID = 6499490875003712720L;

  /** persistent field */
  private ChgOrgRate chgOrgRate = new ChgOrgRate();

  /** persistent field */
  private Emp emp = new Emp();

//  private String empName;

  private Integer empId;

  /** persistent field */
  private BigDecimal oldSalaryBase = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal salaryBase = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal oldOrgPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal oldEmpPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal orgPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal empPay = new BigDecimal(0.00);
  
  /** persistent field */
  private BigDecimal oldOrgRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal oldEmpRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal orgRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal empRate = new BigDecimal(0.00);


  /** persistent field */
  private Integer payStatus = new Integer(0);

  /** persistent field */
  private String reserveaA = "";

  /** persistent field */
  private String reserveaB = "";

  /** persistent field */
  private String reserveaC = "";

  /** 缴额合计 */
//  private Double oldPaySum;
  
  //吴洪涛修改/** 调整前缴额合计 */
  private Double oldOrgPayEmpPaySum;

  /** 调整前应缴总额 oldOrgEmpPaySum(一个职工相加oldPaySum)： */
  private BigDecimal oldOrgEmpPaySum = new BigDecimal(0.00);

  /** 调整后应缴额总额OrgEmpPaySum(一个职工相加paySum) */
  private BigDecimal  orgEmpPaySum  = new BigDecimal(0.00);

  /** full constructor */
  public ChgOrgRateTail(ChgOrgRate chgPaymentHead, Integer empId,
      BigDecimal oldSalaryBase, BigDecimal salaryBase, BigDecimal oldOrgPay,
      BigDecimal oldEmpPay, BigDecimal orgPay, BigDecimal empPay,
      Integer payStatus) {

    this.chgOrgRate = chgPaymentHead;
    this.empId = empId;
    this.oldSalaryBase = oldSalaryBase;
    this.salaryBase = salaryBase;
    this.oldOrgPay = oldOrgPay;
    this.oldEmpPay = oldEmpPay;
    this.orgPay = orgPay;
    this.empPay = empPay;
    this.payStatus = payStatus;
  }

  /** full constructor */
  public ChgOrgRateTail(ChgOrgRate chgPaymentHead, Emp empId,
      BigDecimal oldSalaryBase, BigDecimal salaryBase, BigDecimal oldOrgPay,
      BigDecimal oldEmpPay, BigDecimal orgPay, BigDecimal empPay,
      Integer payStatus, String reserveaA, String reserveaB, String reserveaC) {

    this.chgOrgRate = chgPaymentHead;
    this.emp = emp;
    this.oldSalaryBase = oldSalaryBase;
    this.salaryBase = salaryBase;
    this.oldOrgPay = oldOrgPay;
    this.oldEmpPay = oldEmpPay;
    this.orgPay = orgPay;
    this.empPay = empPay;
    this.payStatus = payStatus;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
  }

  /** default constructor */
  public ChgOrgRateTail() {
  }



  public Emp getEmp() {
    return emp;
  }

  public void setEmp(Emp emp) {
    this.emp = emp;
  }

  public BigDecimal getOldSalaryBase() {
    return this.oldSalaryBase;
  }

  public void setOldSalaryBase(BigDecimal oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
  }

  public BigDecimal getSalaryBase() {
    return this.salaryBase;
  }

  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }

  public BigDecimal getOldOrgPay() {
    return this.oldOrgPay;
  }

  public void setOldOrgPay(BigDecimal oldOrgPay) {
    this.oldOrgPay = oldOrgPay;
  }

  public BigDecimal getOldEmpPay() {
    return this.oldEmpPay;
  }

  public void setOldEmpPay(BigDecimal oldEmpPay) {
    this.oldEmpPay = oldEmpPay;
  }

  public BigDecimal getOrgPay() {
    return this.orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public BigDecimal getEmpPay() {
    return this.empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof ChgOrgRateTail))
      return false;
    ChgOrgRateTail castOther = (ChgOrgRateTail) other;
    return new EqualsBuilder().append(this.getId(), castOther.getId())
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).toHashCode();
  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
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

  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }

//  public Double getOldPaySum() {
//    return oldPaySum;
//  }
//
//  public void setOldPaySum(Double oldPaySum) {
//    this.oldPaySum = oldPaySum;
//  }

//  public String getEmpName() {
//    return empName;
//  }
//
//  public void setEmpName(String empName) {
//    this.empName = empName;
//  }

 

  public BigDecimal getOldOrgEmpPaySum() {
    return oldOrgEmpPaySum;
  }

  public void setOldOrgEmpPaySum(BigDecimal oldOrgEmpPaySum) {
    this.oldOrgEmpPaySum = oldOrgEmpPaySum;
  }

  public BigDecimal getOrgEmpPaySum() {
    return orgEmpPaySum;
  }

  public void setOrgEmpPaySum(BigDecimal orgEmpPaySum) {
    this.orgEmpPaySum = orgEmpPaySum;
  }

  public Double getOldOrgPayEmpPaySum() {
    return oldOrgPayEmpPaySum;
  }

  public void setOldOrgPayEmpPaySum(Double oldOrgPayEmpPaySum) {
    this.oldOrgPayEmpPaySum = oldOrgPayEmpPaySum;
  }

  public BigDecimal getEmpRate() {
    return empRate;
  }

  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }

  public BigDecimal getOldEmpRate() {
    return oldEmpRate;
  }

  public void setOldEmpRate(BigDecimal oldEmpRate) {
    this.oldEmpRate = oldEmpRate;
  }

  public BigDecimal getOldOrgRate() {
    return oldOrgRate;
  }

  public void setOldOrgRate(BigDecimal oldOrgRate) {
    this.oldOrgRate = oldOrgRate;
  }

  public BigDecimal getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }

  public ChgOrgRate getChgOrgRate() {
    return chgOrgRate;
  }

  public void setChgOrgRate(ChgOrgRate chgOrgRate) {
    this.chgOrgRate = chgOrgRate;
  }

 

}
