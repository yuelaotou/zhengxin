package org.xpup.hafmis.syscollection.collloanback.dto;

import java.math.BigDecimal;

public class CollLoanbackTcListDTO {

  private String orgId = "";
  private String orgName = "";
  private String empId = "";
  private String empName = "";
  private BigDecimal checkMoney = new BigDecimal("0.00");
  private String contractId = "";
  private String borrowerName = "";
  private String bizDate = "";
  
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public BigDecimal getCheckMoney() {
    return checkMoney;
  }
  public void setCheckMoney(BigDecimal checkMoney) {
    this.checkMoney = checkMoney;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
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
}
