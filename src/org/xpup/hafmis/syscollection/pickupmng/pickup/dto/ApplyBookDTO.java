package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import java.math.BigDecimal;

public class ApplyBookDTO {
  private String headid;
  private String sOrgName;
  private String sOrgNumber;
  private String sOrgBank;
  private String fOrgName;
  private String fOrgNumber;
  private String fOrgBank;
  private BigDecimal pickBalance;
  private String orgid;
  private String docnum;
  private String bizdate;
  private String operater;
  private String empid;
  private String empcardid;
  private String empname;
  private String reason;
  private String empbankid;
  private String pickstatus;
  private String other_card_num="";
  private String note_num="";
  private String checkperson="";
  public String getCheckperson() {
    return checkperson;
  }
  public void setCheckperson(String checkperson) {
    this.checkperson = checkperson;
  }
  public String getOther_card_num() {
    return other_card_num;
  }
  public void setOther_card_num(String other_card_num) {
    this.other_card_num = other_card_num;
  }
  public String getFOrgBank() {
    return fOrgBank;
  }
  public void setFOrgBank(String orgBank) {
    fOrgBank = orgBank;
  }
  public String getFOrgName() {
    return fOrgName;
  }
  public void setFOrgName(String orgName) {
    fOrgName = orgName;
  }
  public String getFOrgNumber() {
    return fOrgNumber;
  }
  public void setFOrgNumber(String orgNumber) {
    fOrgNumber = orgNumber;
  }
 
  public String getSOrgName() {
    return sOrgName;
  }
  public void setSOrgName(String orgName) {
    sOrgName = orgName;
  }
  public String getSOrgNumber() {
    return sOrgNumber;
  }
  public void setSOrgNumber(String orgNumber) {
    sOrgNumber = orgNumber;
  }
  public String getSOrgBank() {
    return sOrgBank;
  }
  public void setSOrgBank(String orgBank) {
    sOrgBank = orgBank;
  }
  public BigDecimal getPickBalance() {
    return pickBalance;
  }
  public void setPickBalance(BigDecimal pickBalance) {
    this.pickBalance = pickBalance;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getBizdate() {
    return bizdate;
  }
  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }
  public String getDocnum() {
    return docnum;
  }
  public void setDocnum(String docnum) {
    this.docnum = docnum;
  }
  public String getOperater() {
    return operater;
  }
  public void setOperater(String operater) {
    this.operater = operater;
  }
  public String getEmpbankid() {
    return empbankid;
  }
  public void setEmpbankid(String empbankid) {
    this.empbankid = empbankid;
  }
  public String getEmpcardid() {
    return empcardid;
  }
  public void setEmpcardid(String empcardid) {
    this.empcardid = empcardid;
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
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public String getHeadid() {
    return headid;
  }
  public void setHeadid(String headid) {
    this.headid = headid;
  }
  public String getPickstatus() {
    return pickstatus;
  }
  public void setPickstatus(String pickstatus) {
    this.pickstatus = pickstatus;
  }
  public String getNote_num() {
    return note_num;
  }
  public void setNote_num(String note_num) {
    this.note_num = note_num;
  }
  
}
