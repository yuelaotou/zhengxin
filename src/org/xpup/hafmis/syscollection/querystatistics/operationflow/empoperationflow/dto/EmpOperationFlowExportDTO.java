package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;



public class EmpOperationFlowExportDTO  implements ExpDto {

  private static final long serialVersionUID = 157830469042818336L;
  
  private String orgid = "";

  private String orgname = "";
  
  private String empid ="";
  
  private String empname = "";
  
  private String noteNum = "";
  
  private String docNum = "";
   
  private String opStatus = "";
  
  private String opType = "";
  
  private String opDate = "";
  
  private String opMoney = "";
  
  private String opDirection = "";
  
  private String opInterest = "";


 

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




  public String getEmpid() {
    return empid;
  }




  public void setEmpid(String empid) {
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



  public String getInfo(String s) {
    // TODO Auto-generated method stub
    if(s.equals("orgid"))
      return orgid;
    if(s.equals("orgname"))
        return orgname;
    if(s.equals("empid"))
        return empid;
    if(s.equals("empname"))
      return empname;
    if(s.equals("noteNum"))
      return noteNum;
    if(s.equals("docNum"))
        return docNum;
    if(s.equals("opStatus"))
        return opStatus;
    if(s.equals("opType"))
      return opType;
    if(s.equals("opDate"))
        return opDate;
    if(s.equals("opMoney"))
        return opMoney;
    if(s.equals("opDirection"))
      return opDirection;
    if(s.equals("opInterest"))
      return opInterest;
    else
        return null;
  }
  
}

 
