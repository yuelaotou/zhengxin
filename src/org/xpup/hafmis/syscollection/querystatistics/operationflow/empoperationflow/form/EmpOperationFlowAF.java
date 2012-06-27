package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class EmpOperationFlowAF extends CriterionsAF {

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
  
  private String inOpDate = "";
  
  private String opMoney = "";
  
  private String inOpMoney = "";
  
  private String opDirection = "";
  
  private Map opStatusMap = new HashMap();
  
  private Map opTypeMap = new HashMap();


 

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




  public Map getOpStatusMap() {
    return opStatusMap;
  }




  public void setOpStatusMap(Map opStatusMap) {
    this.opStatusMap = opStatusMap;
  }




  public String getOpType() {
    return opType;
  }




  public void setOpType(String opType) {
    this.opType = opType;
  }




  public Map getOpTypeMap() {
    return opTypeMap;
  }




  public void setOpTypeMap(Map opTypeMap) {
    this.opTypeMap = opTypeMap;
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




  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    orgid = "";
    orgname = "";
    empid = "";
    empname = "";
    noteNum = "";
    docNum = "";
    opStatus = "";
    opType = "";
    opDate = "";
    opMoney = "";
    opDirection = "" ;
  }




  public String getInOpDate() {
    return inOpDate;
  }




  public void setInOpDate(String inOpDate) {
    this.inOpDate = inOpDate;
  }




  public String getInOpMoney() {
    return inOpMoney;
  }




  public void setInOpMoney(String inOpMoney) {
    this.inOpMoney = inOpMoney;
  }

 
}