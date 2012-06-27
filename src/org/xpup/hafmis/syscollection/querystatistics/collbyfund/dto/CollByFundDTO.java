package org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto;

import java.math.BigDecimal;

public class CollByFundDTO {

  private BigDecimal orgId = new BigDecimal(0);

  private String orgName;

  private String contractId = "";

  private String yearMonth = "";

  private BigDecimal money = new BigDecimal(0.00);

  private BigDecimal empId = new BigDecimal(0);

  private String empName;

  private String kouDate;

  private String batchNum;

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public BigDecimal getEmpId() {
    return empId;
  }

  public void setEmpId(BigDecimal empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getKouDate() {
    return kouDate;
  }

  public void setKouDate(String kouDate) {
    this.kouDate = kouDate;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getOrgId() {
    return orgId;
  }

  public void setOrgId(BigDecimal orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }
}
