package org.xpup.hafmis.syscollection.paymng.monthpay.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MonthpayTbWindowDto implements Serializable{


  private static final long serialVersionUID = 7824999389240436586L;
  
  private Serializable empid="";
  private String empName="";
  private String payMonth="";
  private String orgpay="";
  private String emppay="";
  private String sumpay="";
  private String empStatus="";
  private String counts="";
  private String orgname="";
  private String orgid="";
  
  private String collectionBank="";
  private String maker="";
  private String bizDate="";

  private BigDecimal salaryBase = new BigDecimal(0.00);
  private BigDecimal orgRate = new BigDecimal(0.00);
  private BigDecimal empRate = new BigDecimal(0.00);
  

  public BigDecimal getEmpRate() {
    return empRate;
  }
  public BigDecimal getOrgRate() {
    return orgRate;
  }
  public BigDecimal getSalaryBase() {
    return salaryBase;
  }
  public String getCounts() {
    return counts;
  }
  public void setCounts(String counts) {
    this.counts = counts;
  }
  public Serializable getEmpid() {
    return empid;
  }
  public void setEmpid(Serializable empid) {
    this.empid = empid;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getEmppay() {
    return emppay;
  }
  public void setEmppay(String emppay) {
    this.emppay = emppay;
  }
  public String getEmpStatus() {
    return empStatus;
  }
  public void setEmpStatus(String empStatus) {
    this.empStatus = empStatus;
  }
  public String getOrgpay() {
    return orgpay;
  }
  public void setOrgpay(String orgpay) {
    this.orgpay = orgpay;
  }
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }
  public String getSumpay() {
    return sumpay;
  }
  public void setSumpay(String sumpay) {
    this.sumpay = sumpay;
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
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getCollectionBank() {
    return collectionBank;
  }
  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }
  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }
  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }
  


}
