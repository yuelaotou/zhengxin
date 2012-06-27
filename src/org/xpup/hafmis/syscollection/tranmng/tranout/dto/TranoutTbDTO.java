package org.xpup.hafmis.syscollection.tranmng.tranout.dto;

import java.io.Serializable;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;



public class TranoutTbDTO extends DomainObject{

  
  private Serializable orgOutid; 
  private String orgOutName;
  private Serializable orgInid; 
  private String orgInName;
  private String noteNum;
  private String docNum;
  private String counts;
  private String money;
  private String interest;
  private String sumMoney;
  private String setDate;
  private String status;
  private String temp_pickStatus;
  public String getTemp_pickStatus() {
    return temp_pickStatus;
  }
  public void setTemp_pickStatus(String temp_pickStatus) {
    this.temp_pickStatus = temp_pickStatus;
  }
  public String getCounts() {
    return counts;
  }
  public void setCounts(String counts) {
    this.counts = counts;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public String getMoney() {
    return money;
  }
  public void setMoney(String money) {
    this.money = money;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public Serializable getOrgInid() {
    return orgInid;
  }
  public void setOrgInid(Serializable orgInid) {
    this.orgInid = orgInid;
  }
  public String getOrgInName() {
    return orgInName;
  }
  public void setOrgInName(String orgInName) {
    this.orgInName = orgInName;
  }
  public Serializable getOrgOutid() {
    return orgOutid;
  }
  public void setOrgOutid(Serializable orgOutid) {
    this.orgOutid = orgOutid;
  }
  public String getOrgOutName() {
    return orgOutName;
  }
  public void setOrgOutName(String orgOutName) {
    this.orgOutName = orgOutName;
  }
  public String getSetDate() {
    return setDate;
  }
  public void setSetDate(String setDate) {
    this.setDate = setDate;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(String sumMoney) {
    this.sumMoney = sumMoney;
  }
  
  

}
