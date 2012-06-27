package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto;

import java.io.Serializable;



public class EmpOperationFlowDTO  {

  private static final long serialVersionUID = 157830469042818336L;
 
  private String id = "";
  
  private Serializable orgid = "";

  private String orgname = "";
  
  private Serializable empid ="";
  
  private String empname = "";
  
  private String noteNum = "";
  
  private String docNum = "";
   
  private String opStatus = "";
  
  private String opType = "";
  
  private String opDate = "";
  
  private String opMoney = "";
  
  private String opDirection = "";
  
  private String opInterest = "";
  
  private String reserveaA = "";
  
  private String checkPerson = "";
  private String clearPerson = "";
  private String reason = "";


 

  public String getOpInterest() {
    return opInterest;
  }




  public void setOpInterest(String opInterest) {
    this.opInterest = opInterest;
  }




  public String getDocNum() {
    return docNum;
  }




  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }




  public Serializable getEmpid() {
    return empid;
  }




  public void setEmpid(Serializable empid) {
    this.empid = empid;
  }




  public String getEmpname() {
    return empname;
  }




  public void setEmpname(String empname) {
    this.empname = empname;
  }




  public String getNoteNum() {
    return noteNum;
  }




  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }




  public String getOpDate() {
    return opDate;
  }




  public void setOpDate(String opDate) {
    this.opDate = opDate;
  }




  public String getOpDirection() {
    return opDirection;
  }




  public void setOpDirection(String opDirection) {
    this.opDirection = opDirection;
  }




  public String getOpMoney() {
    return opMoney;
  }




  public void setOpMoney(String opMoney) {
    this.opMoney = opMoney;
  }




  public String getOpStatus() {
    return opStatus;
  }




  public void setOpStatus(String opStatus) {
    this.opStatus = opStatus;
  }






  public String getOpType() {
    return opType;
  }




  public void setOpType(String opType) {
    this.opType = opType;
  }


  public Serializable getOrgid() {
    return orgid;
  }




  public void setOrgid(Serializable orgid) {
    this.orgid = orgid;
  }




  public String getOrgname() {
    return orgname;
  }




  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }




  public String getId() {
    return id;
  }




  public void setId(String id) {
    this.id = id;
  }




  public String getCheckPerson() {
    return checkPerson;
  }




  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }




  public String getReserveaA() {
    return reserveaA;
  }




  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }




  public String getReason() {
    return reason;
  }




  public void setReason(String reason) {
    this.reason = reason;
  }




  public String getClearPerson() {
    return clearPerson;
  }




  public void setClearPerson(String clearPerson) {
    this.clearPerson = clearPerson;
  }

 
}