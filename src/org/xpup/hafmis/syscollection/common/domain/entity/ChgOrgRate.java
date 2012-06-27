package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class ChgOrgRate extends DomainObject implements Serializable {

  private static final long serialVersionUID = 5399593497786914758L;

  /** persistent field */
  private Org org = new Org();

  /** persistent field */
  private String chgMonth = "";

  /** persistent field */
  private BigDecimal preOrgRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal preEmpRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal orgRate = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal empRate = new BigDecimal(0.00);

  /** persistent field */
  private Integer chgPersonCount = new Integer(0);

  /** persistent field */
  private BigDecimal oldOrgPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal oldEmpPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal orgPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal empPay = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal salarybase = new BigDecimal(0.00);

  private BigDecimal preSumPay = new BigDecimal(0.00);

  private BigDecimal sumPay = new BigDecimal(0.00);

  private BigDecimal sumPreSumPay = new BigDecimal(0.00);

  private BigDecimal sumSumPay = new BigDecimal(0.00);

  private BigDecimal rate = new BigDecimal(0.00);

  private BigDecimal empCount = new BigDecimal(0.00);

  private String rate_ = "";

  private BigDecimal pay = new BigDecimal(0.00);

  private String month = "";

  /** persistent field */
  private String bizDate = "";

  /** persistent field */
  private Integer chgStatus = new Integer(0);

  private Integer chgStatus_ = new Integer(0);

  /** persistent field */
  private String temp_chgStatus = "";

  /** nullable persistent field */
  private PaymentHead paymentHead;

  /** persistent field */
  private String reserveaA = "";

  /** persistent field */
  private String reserveaB = "";

  /** persistent field */
  private String reserveaC = "";

  private String character = "";

  // ÎâºéÌÎÐÞ¸Ä 2008£­3£­19 µ¥Î»°æ
  private String orgEdition;

  private Integer personCount = new Integer(0);;

  public Integer getPersonCount() {
    return personCount;
  }

  public void setPersonCount(Integer personCount) {
    this.personCount = personCount;
  }

  /** full constructor */
  public ChgOrgRate(Org org, String chgMonth, BigDecimal orgRate,
      BigDecimal empRate, Integer chgPersonCount, BigDecimal oldOrgPay,
      BigDecimal oldEmpPay, BigDecimal orgPay, BigDecimal empPay,
      String bizDate, Integer chgStatus, PaymentHead paymentHead,
      String reserveaA, String reserveaB, String reserveaC,
      BigDecimal preOrgRate, BigDecimal preEmpRate) {

    this.org = org;
    this.chgMonth = chgMonth;
    this.orgRate = orgRate;
    this.empRate = empRate;
    this.chgPersonCount = chgPersonCount;
    this.oldOrgPay = oldOrgPay;
    this.oldEmpPay = oldEmpPay;
    this.orgPay = orgPay;
    this.empPay = empPay;
    this.bizDate = bizDate;
    this.chgStatus = chgStatus;
    this.paymentHead = paymentHead;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.preOrgRate = preOrgRate;
    this.preEmpRate = preEmpRate;
  }

  /** default constructor */
  public ChgOrgRate() {
  }

  /** minimal constructor */
  public ChgOrgRate(Org org, String chgMonth, BigDecimal orgRate,
      BigDecimal empRate, Integer chgPersonCount, BigDecimal oldOrgPay,
      BigDecimal oldEmpPay, BigDecimal orgPay, BigDecimal empPay,
      String bizDate, Integer chgStatus, BigDecimal preOrgRate,
      BigDecimal preEmpRate) {

    this.org = org;
    this.chgMonth = chgMonth;
    this.orgRate = orgRate;
    this.empRate = empRate;
    this.chgPersonCount = chgPersonCount;
    this.oldOrgPay = oldOrgPay;
    this.oldEmpPay = oldEmpPay;
    this.orgPay = orgPay;
    this.empPay = empPay;
    this.bizDate = bizDate;
    this.chgStatus = chgStatus;
    this.preOrgRate = preOrgRate;
    this.preEmpRate = preEmpRate;
  }

  public Org getOrg() {
    return this.org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public String getChgMonth() {
    return this.chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
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

  public Integer getChgPersonCount() {
    return this.chgPersonCount;
  }

  public void setChgPersonCount(Integer chgPersonCount) {
    this.chgPersonCount = chgPersonCount;
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

  public String getBizDate() {
    return this.bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public Integer getChgStatus() {
    return this.chgStatus;
  }

  public void setChgStatus(Integer chgStatus) {
    this.chgStatus = chgStatus;
  }

  public PaymentHead getPaymentHead() {
    return paymentHead;
  }

  public void setPaymentHead(PaymentHead paymentHead) {
    this.paymentHead = paymentHead;
  }

  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof ChgOrgRate))
      return false;
    ChgOrgRate castOther = (ChgOrgRate) other;
    return new EqualsBuilder().append(this.getId(), castOther.getId())
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).toHashCode();
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

  public BigDecimal getPreEmpRate() {
    return preEmpRate;
  }

  public void setPreEmpRate(BigDecimal preEmpRate) {
    this.preEmpRate = preEmpRate;
  }

  public BigDecimal getPreOrgRate() {
    return preOrgRate;
  }

  public void setPreOrgRate(BigDecimal preOrgRate) {
    this.preOrgRate = preOrgRate;
  }

  public BigDecimal getPreSumPay() {
    return preSumPay;
  }

  public void setPreSumPay(BigDecimal preSumPay) {
    this.preSumPay = preSumPay;
  }

  public BigDecimal getSumPay() {
    return sumPay;
  }

  public void setSumPay(BigDecimal sumPay) {
    this.sumPay = sumPay;
  }

  public String getTemp_chgStatus() {
    return temp_chgStatus;
  }

  public void setTemp_chgStatus(String temp_chgStatus) {
    this.temp_chgStatus = temp_chgStatus;
  }

  public BigDecimal getSalarybase() {
    return salarybase;
  }

  public void setSalarybase(BigDecimal salarybase) {
    this.salarybase = salarybase;
  }

  public BigDecimal getSumPreSumPay() {
    return sumPreSumPay;
  }

  public void setSumPreSumPay(BigDecimal sumPreSumPay) {
    this.sumPreSumPay = sumPreSumPay;
  }

  public BigDecimal getSumSumPay() {
    return sumSumPay;
  }

  public void setSumSumPay(BigDecimal sumSumPay) {
    this.sumSumPay = sumSumPay;
  }

  public Integer getChgStatus_() {
    return chgStatus_;
  }

  public void setChgStatus_(Integer chgStatus_) {
    this.chgStatus_ = chgStatus_;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public String getRate_() {
    return rate_;
  }

  public void setRate_(String rate_) {
    this.rate_ = rate_;
  }

  public String getOrgEdition() {
    return orgEdition;
  }

  public void setOrgEdition(String orgEdition) {
    this.orgEdition = orgEdition;
  }

  public BigDecimal getEmpCount() {
    return empCount;
  }

  public void setEmpCount(BigDecimal empCount) {
    this.empCount = empCount;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public BigDecimal getPay() {
    return pay;
  }

  public void setPay(BigDecimal pay) {
    this.pay = pay;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

}
