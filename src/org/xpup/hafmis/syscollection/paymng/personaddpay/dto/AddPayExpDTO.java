package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class AddPayExpDTO implements ExpDto{

  private static final long serialVersionUID = 0L;

  private String orgId="";

  private String orgName="";

  private String docNum="";

  private String empId="";
  
  private String empName="";
  
  private String  orgPay="";
  
  private String  empPay="";
  
  private String beginMonth="";
  
  private String endMonth="";
  
  private String reason="";

  private String noteNum="";
  
  private String paySum="";
  
  private String addPayType="";


  public String getPaySum() {
    return paySum;
  }




  public void setPaySum(String paySum) {
    this.paySum = paySum;
  }




  public String getNoteNum() {
    return noteNum;
  }




  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }




  public static long getSerialVersionUID() {
    return serialVersionUID;
  }




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




  public String getInfo(String s) {
    if(s.equals("orgId"))
      return orgId;
    if(s.equals("orgName"))
        return orgName;
    if(s.equals("docNum"))
        return docNum;
    if(s.equals("empId"))
      return empId;
    if(s.equals("empName"))
      return empName;
    if(s.equals("orgPay"))
      return orgPay;
    if(s.equals("empPay"))
      return empPay;
    if(s.equals("beginMonth"))
      return beginMonth;
    if(s.equals("endMonth"))
      return endMonth;
    if(s.equals("reason"))
      return reason;
    if(s.equals("addPayType"))
      return addPayType;
    else 
        return null;
  }




  public String getAddPayType() {
    return addPayType;
  }




  public void setAddPayType(String addPayType) {
    this.addPayType = addPayType;
  }

  
}
