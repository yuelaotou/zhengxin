package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;



public class AddpayInfoDto {

  private static final long serialVersionUID = 0L;

  private String orgid;

  private String orgName;

  private String addpayMonth;
  
  private String addStartPayMonth;

  private String noteNum;
  
  private String empId="";
  
  private String  empName="";
  
  private String orgShouldpay="";
  
  private String empShouldpay ;
  
  private String orgAddpayMoney;
  
  private String empAddpayMoney;
  
  private String empPayStatus;

  private String salaryBase;
  
  private String orgRate;
  
  private String empRate;

  public String getEmpRate() {
    return empRate;
  }

  public void setEmpRate(String empRate) {
    this.empRate = empRate;
  }

  public String getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(String orgRate) {
    this.orgRate = orgRate;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getEmpPayStatus() {
    return empPayStatus;
  }

  public void setEmpPayStatus(String empPayStatus) {
    this.empPayStatus = empPayStatus;
  }

  public String getAddpayMonth() {
    return addpayMonth;
  }

  public void setAddpayMonth(String addpayMonth) {
    this.addpayMonth = addpayMonth;
  }

  public String getEmpAddpayMoney() {
    return empAddpayMoney;
  }

  public void setEmpAddpayMoney(String empAddpayMoney) {
    this.empAddpayMoney = empAddpayMoney;
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

  public String getEmpShouldpay() {
    return empShouldpay;
  }

  public void setEmpShouldpay(String empShouldpay) {
    this.empShouldpay = empShouldpay;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgAddpayMoney() {
    return orgAddpayMoney;
  }

  public void setOrgAddpayMoney(String orgAddpayMoney) {
    this.orgAddpayMoney = orgAddpayMoney;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgShouldpay() {
    return orgShouldpay;
  }

  public void setOrgShouldpay(String orgShouldpay) {
    this.orgShouldpay = orgShouldpay;
  }

  public String getAddStartPayMonth() {
    return addStartPayMonth;
  }

  public void setAddStartPayMonth(String addStartPayMonth) {
    this.addStartPayMonth = addStartPayMonth;
  }
}