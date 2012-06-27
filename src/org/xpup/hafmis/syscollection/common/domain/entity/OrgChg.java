package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class OrgChg extends DomainObject implements Serializable {

  private Org org = new Org();

  private BigDecimal preRate = new BigDecimal(0.00);

  private BigDecimal newRate = new BigDecimal(0.00);

  private BigDecimal payRate = new BigDecimal(0);

  private BigDecimal prePay = new BigDecimal(0.00);

  private BigDecimal newPay = new BigDecimal(0.00);

  private BigDecimal paySalary = new BigDecimal(0.00);

  private String addMonth = "";

  private String addStEndMonth = "";

  private BigDecimal addCount = new BigDecimal(0.00);

  private BigDecimal addSum = new BigDecimal(0.00);

  private BigDecimal addEmp = new BigDecimal(0.00);

  private BigDecimal addOrg = new BigDecimal(0.00);

  private String status = "";

  private String print = "";

  private String make = "";

  private String person = "";

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public BigDecimal getAddCount() {
    return addCount;
  }

  public void setAddCount(BigDecimal addCount) {
    this.addCount = addCount;
  }

  public BigDecimal getAddEmp() {
    return addEmp;
  }

  public void setAddEmp(BigDecimal addEmp) {
    this.addEmp = addEmp;
  }

  public String getAddMonth() {
    return addMonth;
  }

  public void setAddMonth(String addMonth) {
    this.addMonth = addMonth;
  }

  public BigDecimal getAddOrg() {
    return addOrg;
  }

  public void setAddOrg(BigDecimal addOrg) {
    this.addOrg = addOrg;
  }

  public String getAddStEndMonth() {
    return addStEndMonth;
  }

  public void setAddStEndMonth(String addStEndMonth) {
    this.addStEndMonth = addStEndMonth;
  }

  public BigDecimal getAddSum() {
    return addSum;
  }

  public void setAddSum(BigDecimal addSum) {
    this.addSum = addSum;
  }

  public BigDecimal getNewPay() {
    return newPay;
  }

  public void setNewPay(BigDecimal newPay) {
    this.newPay = newPay;
  }

  public BigDecimal getNewRate() {
    return newRate;
  }

  public void setNewRate(BigDecimal newRate) {
    this.newRate = newRate;
  }

  public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public BigDecimal getPayRate() {
    return payRate;
  }

  public void setPayRate(BigDecimal payRate) {
    this.payRate = payRate;
  }

  public BigDecimal getPaySalary() {
    return paySalary;
  }

  public void setPaySalary(BigDecimal paySalary) {
    this.paySalary = paySalary;
  }

  public BigDecimal getPrePay() {
    return prePay;
  }

  public void setPrePay(BigDecimal prePay) {
    this.prePay = prePay;
  }

  public BigDecimal getPreRate() {
    return preRate;
  }

  public void setPreRate(BigDecimal preRate) {
    this.preRate = preRate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPrint() {
    return print;
  }

  public void setPrint(String print) {
    this.print = print;
  }
}
