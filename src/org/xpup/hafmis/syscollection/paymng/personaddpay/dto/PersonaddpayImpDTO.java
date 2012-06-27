package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class PersonaddpayImpDTO extends impDto{
  
 

  private static final long serialVersionUID = 1L;

  private String orgId="";
  
  private String orgName="";
  
  private String docNum="";
  
  private String empId="";
  
  private String empName="";
  
  private String orgPay="";   
  
  private String empPay="";
  
  private String beginMonth="";
  
  private String endMonth="";
  
  private String reason="";

  public String getBeginMonth() {
    return beginMonth;
  }

  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
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

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }




}
