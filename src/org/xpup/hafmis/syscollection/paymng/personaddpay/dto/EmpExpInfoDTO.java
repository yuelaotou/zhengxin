package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

public class EmpExpInfoDTO {

  private String orgId="";

  private String orgName="";

  private String noteNum="";
  
  private String empId="";
  
  private String  empName="";
  
  private String orgPay="";
  
  private String empPay="" ;
  
  private String paySum="";
  
  private String beginMonth="";
  
  private String endMonth="";
  
  private String reason="";
  
  private String addPayType = "";

  public String getAddPayType() {
    return addPayType;
  }

  public void setAddPayType(String addPayType) {
    this.addPayType = addPayType;
  }

  public String getBeginMonth() {
    return beginMonth;
  }

  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
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

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getEndMonth() {
    return endMonth;
  }

  public void setEndMonth(String endMonth) {
    this.endMonth = endMonth;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
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

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }

  public String getPaySum() {
    return paySum;
  }

  public void setPaySum(String paySum) {
    this.paySum = paySum;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
