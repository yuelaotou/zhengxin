package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class BankAssignUserAF extends ActionForm{
  
  private List officelist;
  private List userlist;
  
  private String users="";
  private String offices="";
  
  private String username="";
  private String officename="";
  
  private String officeid;
  private String bankid;
  
  private List userBanklist;
  private List sparelist;
    
  public String getBankid() {
    return bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
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

  public String getOffices() {
    return offices;
  }

  public void setOffices(String offices) {
    this.offices = offices;
  }

  public List getOfficelist() {
    return officelist;
  }

  public void setOfficelist(List officelist) {
    this.officelist = officelist;
  }


  public List getSparelist() {
    return sparelist;
  }

  public void setSparelist(List sparelist) {
    this.sparelist = sparelist;
  }

  public List getUserBanklist() {
    return userBanklist;
  }

  public void setUserBanklist(List userBanklist) {
    this.userBanklist = userBanklist;
  }

  public List getUserlist() {
    return userlist;
  }

  public void setUserlist(List userlist) {
    this.userlist = userlist;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsers() {
    return users;
  }

  public void setUsers(String users) {
    this.users = users;
  }


}
