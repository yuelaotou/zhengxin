package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrgaddpayMaintainDto implements Serializable{


  private static final long serialVersionUID = 7824999389240436586L;
  
  private Serializable orgId="";
  private String orgName="";
  private String payMonth="";
  private String startPayMonth="";
  private String noteNum="";
  private String docNum="";
  private String opTime="";
  private String payStatus="";
  private String payMode="";
  private String id="";
  private String personCounts="";
  private String settDate="";
  private String commitStatus="";//ww 单位版提交状态
  private BigDecimal pay=new BigDecimal(0.00);
  
  
  public BigDecimal getPay() {
    return pay;
  }
  public void setPay(BigDecimal pay) {
    this.pay = pay;
  }
  public String getPayMode() {
    return payMode;
  }
  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }
  public Serializable getOrgId() {
    return orgId;
  }
  public void setOrgId(Serializable orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPersonCounts() {
    return personCounts;
  }
  public void setPersonCounts(String personCounts) {
    this.personCounts = personCounts;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public String getStartPayMonth() {
    return startPayMonth;
  }
  public void setStartPayMonth(String startPayMonth) {
    this.startPayMonth = startPayMonth;
  }
  public String getCommitStatus() {
    return commitStatus;
  }
  public void setCommitStatus(String commitStatus) {
    this.commitStatus = commitStatus;
  }
 

}
