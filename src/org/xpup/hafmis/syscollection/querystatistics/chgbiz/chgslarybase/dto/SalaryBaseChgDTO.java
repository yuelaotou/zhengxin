package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.dto;

import java.math.BigDecimal;

public class SalaryBaseChgDTO {

  private String id;

  private String orgid;

  private String orgname;

  private int personCount;

  private String orgrate;

  private String emprate;

  private BigDecimal preSalary = new BigDecimal(0.00);

  private BigDecimal curSalary = new BigDecimal(0.00);

  private BigDecimal prePay = new BigDecimal(0.00);

  private BigDecimal curPay = new BigDecimal(0.00);

  private BigDecimal payless = new BigDecimal(0.00);

  private String payMonth;

  private String chgMonth;

  private String bizdate;
  
  private String chgStatus;

  public String getChgStatus() {
    return chgStatus;
  }

  public void setChgStatus(String chgStatus) {
    this.chgStatus = chgStatus;
  }

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public BigDecimal getCurPay() {
    return curPay;
  }

  public void setCurPay(BigDecimal curPay) {
    this.curPay = curPay;
  }

  public BigDecimal getCurSalary() {
    return curSalary;
  }

  public void setCurSalary(BigDecimal curSalary) {
    this.curSalary = curSalary;
  }

  public String getEmprate() {
    return emprate;
  }

  public void setEmprate(String emprate) {
    this.emprate = emprate;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getOrgrate() {
    return orgrate;
  }

  public void setOrgrate(String orgrate) {
    this.orgrate = orgrate;
  }

  public BigDecimal getPayless() {
    return payless;
  }

  public void setPayless(BigDecimal payless) {
    this.payless = payless;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public int getPersonCount() {
    return personCount;
  }

  public void setPersonCount(int personCount) {
    this.personCount = personCount;
  }

  public BigDecimal getPrePay() {
    return prePay;
  }

  public void setPrePay(BigDecimal prePay) {
    this.prePay = prePay;
  }

  public BigDecimal getPreSalary() {
    return preSalary;
  }

  public void setPreSalary(BigDecimal preSalary) {
    this.preSalary = preSalary;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
