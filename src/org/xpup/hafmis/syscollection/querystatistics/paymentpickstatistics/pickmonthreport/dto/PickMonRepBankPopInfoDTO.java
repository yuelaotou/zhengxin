package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto;

import java.math.BigDecimal;

public class PickMonRepBankPopInfoDTO {
  private String orgId = "";
  private String orgName = "";
  private String empId = "";
  private String empName = "";
  private BigDecimal pickMoney = new BigDecimal(0.00);
  private BigDecimal pickInterest = new BigDecimal(0.00);
  private BigDecimal sumPickMoney = new BigDecimal(0.00);
  private String pickReason = "";
  
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public BigDecimal getPickInterest() {
    return pickInterest;
  }
  public void setPickInterest(BigDecimal pickInterest) {
    this.pickInterest = pickInterest;
  }
  public BigDecimal getPickMoney() {
    return pickMoney;
  }
  public void setPickMoney(BigDecimal pickMoney) {
    this.pickMoney = pickMoney;
  }
  public String getPickReason() {
    return pickReason;
  }
  public void setPickReason(String pickReason) {
    this.pickReason = pickReason;
  }
  public BigDecimal getSumPickMoney() {
    return sumPickMoney;
  }
  public void setSumPickMoney(BigDecimal sumPickMoney) {
    this.sumPickMoney = sumPickMoney;
  }
}
