package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.orgstrct.dto.UserToSecurityDTO;

public class UserToSecurityAF extends ActionForm{
  private UserToSecurityDTO userToSecurityDTO = new UserToSecurityDTO();
  private List userlist;
  private List officelist;
  private List banklist;
  
  private String users="";
  private String offices="";
  private String banks="";
  
  private String username="";
  private String officename="";
  private String bankname="";
  
  private String officeid;
  private String bankid;
  private String[] orgid=new String[0];
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

  public String[] getOrgid() {
    return orgid;
  }

  public void setOrgid(String[] orgid) {
    this.orgid = orgid;
  }

  public String getBankname() {
    return bankname;
  }

  public void setBankname(String bankname) {
    this.bankname = bankname;
  }

  public String getOfficename() {
    return officename;
  }

  public void setOfficename(String officename) {
    this.officename = officename;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBanks() {
    return banks;
  }

  public void setBanks(String banks) {
    this.banks = banks;
  }

  public String getOffices() {
    return offices;
  }

  public void setOffices(String offices) {
    this.offices = offices;
  }

  public List getBanklist() {
    return banklist;
  }

  public void setBanklist(List banklist) {
    this.banklist = banklist;
  }

  public List getOfficelist() {
    return officelist;
  }

  public void setOfficelist(List officelist) {
    this.officelist = officelist;
  }

  public List getUserlist() {
    return userlist;
  }

  public void setUserlist(List userlist) {
    this.userlist = userlist;
  }

  public String getUsers() {
    return users;
  }

  public void setUsers(String users) {
    this.users = users;
  }

  public UserToSecurityDTO getUserToSecurityDTO() {
    return userToSecurityDTO;
  }

  public void setUserToSecurityDTO(UserToSecurityDTO userToSecurityDTO) {
    this.userToSecurityDTO = userToSecurityDTO;
  }


}
