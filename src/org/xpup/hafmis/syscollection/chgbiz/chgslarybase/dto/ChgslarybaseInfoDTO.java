package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto;
 
public class ChgslarybaseInfoDTO {
  private String orgId;

  private String orgName;

  private String chgMonth;

  private String empId;

  private String empName;

  private String cardNum;

  private String oldSalaryBase;

  private String salaryBase;

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
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

  public String getOldSalaryBase() {
    return oldSalaryBase;
  }

  public void setOldSalaryBase(String oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
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

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }
}
