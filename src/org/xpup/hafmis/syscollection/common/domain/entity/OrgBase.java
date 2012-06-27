package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public class OrgBase extends DomainObject{

  /** persistent field */
  private OrgInfo orgInfo = new OrgInfo();

  /** persistent field */
  private BigDecimal payMode=new BigDecimal(0.00);
  
  private String payModeStr = "";

  private Integer orgPayCount;
  private BigDecimal sumPay=new BigDecimal(0.00);
  /**
   * 职工合计
   */
  private Integer orgCount;
  /**
   * 工资基数合计
   */
  private BigDecimal salaryBaseCount=new BigDecimal(0.00);
  /**
   * 单位缴额合计
   */
  private BigDecimal orgpaySumCount=new BigDecimal(0.00);
  /**
   * 职工缴额合计
   */
  private BigDecimal emppaySumCount=new BigDecimal(0.00);
  /**
   * 总缴额合计
   */
  private BigDecimal sumCount=new BigDecimal(0.00);
  private String temp_payMode="";

  /**
   * 单位代扣号
   */
  private String orgAgentNum = "";




  private String conPayMode;
  /** nullable persistent field */

  private BigDecimal orgRate=new BigDecimal(0.00);

  /** nullable persistent field */

  private BigDecimal empRate=new BigDecimal(0.00);

  /** nullable persistent field */

  private Integer payPrecision=new Integer(0);
  /** nullable persistent field */

  private BigDecimal temp_salary=new BigDecimal(0.00);//存放该单位下职工状态是1、3、4的工资基数合

  /** persistent field */
  private String firstPayMonth;

  /** persistent field */
  private String orgPayMonth;

  /** persistent field */
  private String empPayMonth;


  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;
  private String oldOrgID;
  /** full constructor */
  /** 单位版用的状态*/
  private String orgStatus;
  public String getOrgStatus() {
    return orgStatus;
  }

  public void setOrgStatus(String orgStatus) {
    this.orgStatus = orgStatus;
  }

  public OrgBase(OrgInfo orgInfo, BigDecimal payMode, BigDecimal orgRate, BigDecimal empRate, Integer payPrecision, String firstPayMonth, String orgPayMonth, String empPayMonth, String reserveaA, String reserveaB, String reserveaC,String oldOrgID) {
      this.orgInfo = orgInfo;
      this.payMode = payMode;
      this.orgRate = orgRate;
      this.empRate = empRate;
      this.payPrecision = payPrecision;
      this.firstPayMonth = firstPayMonth;
      this.orgPayMonth = orgPayMonth;
      this.empPayMonth = empPayMonth;
      this.reserveaA = reserveaA;
      this.reserveaB = reserveaB;
      this.reserveaC = reserveaC;
      this.oldOrgID = oldOrgID;
  }

  /** default constructor */
  public OrgBase() {
  }

  /** minimal constructor */
  public OrgBase(OrgInfo orgInfo, BigDecimal payMode, String firstPayMonth, String orgPayMonth, String empPayMonth) {
      this.orgInfo = orgInfo;
      this.payMode = payMode;
      this.firstPayMonth = firstPayMonth;
      this.orgPayMonth = orgPayMonth;
      this.empPayMonth = empPayMonth;
  }

  public OrgInfo getOrgInfo() {
      return this.orgInfo;
  }

  public void setOrgInfo(OrgInfo orgInfo) {
      this.orgInfo = orgInfo;
  }

  public BigDecimal getPayMode() {
      return this.payMode;
  }

  public void setPayMode(BigDecimal payMode) {
      this.payMode = payMode;
  }

  public BigDecimal getOrgRate() {
      return this.orgRate;
  }

  public void setOrgRate(BigDecimal orgRate) {
      this.orgRate = orgRate;
  }

  public BigDecimal getEmpRate() {
      return this.empRate;
  }

  public void setEmpRate(BigDecimal empRate) {
      this.empRate = empRate;
  }

  public Integer getPayPrecision() {
      return this.payPrecision;
  }

  public void setPayPrecision(Integer payPrecision) {
      this.payPrecision = payPrecision;
  }

  public String getFirstPayMonth() {
      return this.firstPayMonth;
  }

  public void setFirstPayMonth(String firstPayMonth) {
      this.firstPayMonth = firstPayMonth;
  }

  public String getOrgPayMonth() {
      return this.orgPayMonth;
  }

  public void setOrgPayMonth(String orgPayMonth) {
      this.orgPayMonth = orgPayMonth;
  }

  public String getEmpPayMonth() {
      return this.empPayMonth;
  }

  public void setEmpPayMonth(String empPayMonth) {
      this.empPayMonth = empPayMonth;
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
      if ( !(other instanceof Org) ) return false;
      Org castOther = (Org) other;
      return new EqualsBuilder()
          .append(this.getId(), castOther.getId())
          .isEquals();
  }

  public int hashCode() {
      return new HashCodeBuilder()
          .append(getId())
          .toHashCode();
  }

  public String getOldOrgID() {
    return oldOrgID;
  }

  public void setOldOrgID(String oldOrgID) {
    this.oldOrgID = oldOrgID;
  }


  public Integer getOrgPayCount() {
    return orgPayCount;
  }

  public void setOrgPayCount(Integer orgPayCount) {
    this.orgPayCount = orgPayCount;
  }

  public BigDecimal getSumPay() {
    return sumPay;
  }

  public void setSumPay(BigDecimal sumPay) {
    this.sumPay = sumPay;
  }


  public String getTemp_payMode() {
    return temp_payMode;
  }

  public void setTemp_payMode(String temp_payMode) {
    this.temp_payMode = temp_payMode;
  }


  public Integer getOrgCount() {
    return orgCount;
  }

  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
  }

  public BigDecimal getOrgpaySumCount() {
    return orgpaySumCount;
  }

  public void setOrgpaySumCount(BigDecimal orgpaySumCount) {
    this.orgpaySumCount = orgpaySumCount;
  }

  public BigDecimal getSalaryBaseCount() {
    return salaryBaseCount;
  }

  public void setSalaryBaseCount(BigDecimal salaryBaseCount) {
    this.salaryBaseCount = salaryBaseCount;
  }

  public BigDecimal getEmppaySumCount() {
    return emppaySumCount;
  }

  public void setEmppaySumCount(BigDecimal emppaySumCount) {
    this.emppaySumCount = emppaySumCount;
  }

  public BigDecimal getSumCount() {
    return sumCount;
  }

  public void setSumCount(BigDecimal sumCount) {
    this.sumCount = sumCount;
  }


  public BigDecimal getTemp_salary() {
    return temp_salary;
  }

  public void setTemp_salary(BigDecimal temp_salary) {
    this.temp_salary = temp_salary;
  }

  public String getPayModeStr() {
    return payModeStr;
  }

  public void setPayModeStr(String payModeStr) {
    this.payModeStr = payModeStr;
  }

  public String getConPayMode() {
    return conPayMode;
  }

  public void setConPayMode(String conPayMode) {
    this.conPayMode = conPayMode;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }


}
