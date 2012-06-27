package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;

public class OrgDto implements Serializable {

  private static final long serialVersionUID = -1971085793012543026L;

  private String orgid ="";
  
  private String orgname ="";
  
  private String officeid = "";
  
  private String officename ="";
  
  private String bankid ="";
  
  private String bankname ="";
  
  private String username="";
  
  private String bankStatus;
  //bit add
  private String collectionbankacc="";
  private String contactprsn="";
  private String contacttel="";
  private String centername="";
  
  public String getCentername() {
    return centername;
  }
  public void setCentername(String centername) {
    this.centername = centername;
  }
  public String getCollectionbankacc() {
    return collectionbankacc;
  }
  public void setCollectionbankacc(String collectionbankacc) {
    this.collectionbankacc = collectionbankacc;
  }
  public String getContactprsn() {
    return contactprsn;
  }
  public void setContactprsn(String contactprsn) {
    this.contactprsn = contactprsn;
  }
  public String getContacttel() {
    return contacttel;
  }
  public void setContacttel(String contacttel) {
    this.contacttel = contacttel;
  }
  public String getBankStatus() {
    return bankStatus;
  }
  public void setBankStatus(String bankStatus) {
    this.bankStatus = bankStatus;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getBankid() {
    return bankid;
  }
  public void setBankid(String bankid) {
    this.bankid = bankid;
  }
  public String getBankname() {
    return bankname;
  }
  public void setBankname(String bankname) {
    this.bankname = bankname;
  }
  public String getOfficeid() {
    return officeid;
  }
  public void setOfficeid(String officeid) {
    this.officeid = officeid;
  }
  public String getOfficename() {
    return officename;
  }
  public void setOfficename(String officename) {
    this.officename = officename;
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
  
}
