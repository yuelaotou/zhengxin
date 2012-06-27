package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class RoleToSecurityAF extends ActionForm{
  
  private List officelist;
  private List banklist;
  private List rolelist;
  
  private String roles="";
  private String offices="";
  private String banks="";
  
  private String rolename="";
  private String officename="";
  private String bankname="";
  
  private String officeid;
  private String bankid;
  
  private List roleOrglist;
  private List sparelist;
  
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

  public List getRolelist() {
    return rolelist;
  }

  public void setRolelist(List rolelist) {
    this.rolelist = rolelist;
  }

  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public List getRoleOrglist() {
    return roleOrglist;
  }

  public void setRoleOrglist(List roleOrglist) {
    this.roleOrglist = roleOrglist;
  }

  public List getSparelist() {
    return sparelist;
  }

  public void setSparelist(List sparelist) {
    this.sparelist = sparelist;
  }

}
