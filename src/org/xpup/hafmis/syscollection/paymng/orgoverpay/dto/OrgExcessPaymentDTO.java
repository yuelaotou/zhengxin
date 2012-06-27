package org.xpup.hafmis.syscollection.paymng.orgoverpay.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrgExcessPaymentDTO implements Serializable{


  private static final long serialVersionUID = 7824999389240436586L;
  
  private Serializable orgId="";
  private String orgName="";
  private String payMonth="";
  private String noteNum="";
  private String docNum="";
  private String opTime="";
  private String payStatus="";
  private String id="";
  private String settDate="";
  private BigDecimal payMoney=new BigDecimal(0.00);

  public BigDecimal getPayMoney() {
    return payMoney;
  }
  public void setPayMoney(BigDecimal payMoney) {
    this.payMoney = payMoney;
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
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
 

}
