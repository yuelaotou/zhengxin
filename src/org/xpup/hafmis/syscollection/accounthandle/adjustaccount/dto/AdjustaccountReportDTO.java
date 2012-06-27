package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class AdjustaccountReportDTO implements Serializable{

  private static final long serialVersionUID = 0L;
  private BigDecimal adjustaccount=new BigDecimal(0.00);
  private String wrongdocnum;
  private String empname;
  private String empid;
  private String empidcard;
  private String wrongaccountdate;
  private String bis_type;
  private String adjustMoney="";
  private String orgid="";
  private String orgname="";
  private String officename="";
  private String collBankName="";
  private String openBank="";
  private String officeid="";
  private String noteNum = "";
  private String openBankAcc = "";
  private String checkPerson = "";
  private String clearPerson = "";
  
  private String badReason="";
  public String getCheckPerson() {
    return checkPerson;
  }
  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }
  public String getClearPerson() {
    return clearPerson;
  }
  public void setClearPerson(String clearPerson) {
    this.clearPerson = clearPerson;
  }
  public String getOpenBankAcc() {
    return openBankAcc;
  }
  public void setOpenBankAcc(String openBankAcc) {
    this.openBankAcc = openBankAcc;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public String getCollBankName() {
    return collBankName;
  }
  public void setCollBankName(String collBankName) {
    this.collBankName = collBankName;
  }
  public String getOfficeid() {
    return officeid;
  }
  public void setOfficeid(String officeid) {
    this.officeid = officeid;
  }
  public String getOpenBank() {
    return openBank;
  }
  public void setOpenBank(String openBank) {
    this.openBank = openBank;
  }
  public String getOfficename() {
    return officename;
  }
  public void setOfficename(String officename) {
    this.officename = officename;
  }
  public String getAdjustMoney() {
    return adjustMoney;
  }
  public void setAdjustMoney(String adjustMoney) {
    this.adjustMoney = adjustMoney;
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
  public BigDecimal getAdjustaccount() {
    return adjustaccount;
  }
  public void setAdjustaccount(BigDecimal adjustaccount) {
    this.adjustaccount = adjustaccount;
  }
  public String getBis_type() {
    return bis_type;
  }
  public void setBis_type(String bis_type) {
    this.bis_type = bis_type;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpidcard() {
    return empidcard;
  }
  public void setEmpidcard(String empidcard) {
    this.empidcard = empidcard;
  }
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getWrongaccountdate() {
    return wrongaccountdate;
  }
  public void setWrongaccountdate(String wrongaccountdate) {
    this.wrongaccountdate = wrongaccountdate;
  }
  public String getWrongdocnum() {
    return wrongdocnum;
  }
  public void setWrongdocnum(String wrongdocnum) {
    this.wrongdocnum = wrongdocnum;
  }
  public String getBadReason() {
    return badReason;
  }
  public void setBadReason(String badReason) {
    this.badReason = badReason;
  }
}
