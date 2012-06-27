package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserAssignUsersAF extends ActionForm{

  private List userlist;
  
  private String users="";
  
  private String username="";
  
  
  private List userAuserlist;
  private List sparelist;
  
  

  public List getSparelist() {
    return sparelist;
  }

  public void setSparelist(List sparelist) {
    this.sparelist = sparelist;
  }

  public List getUserlist() {
    return userlist;
  }

  public void setUserlist(List userlist) {
    this.userlist = userlist;
  }

  public List getUserAuserlist() {
    return userAuserlist;
  }

  public void setUserAuserlist(List userAuserlist) {
    this.userAuserlist = userAuserlist;
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
