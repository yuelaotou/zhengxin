package org.xpup.hafmis.orgstrct.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserAssignBookAF extends ActionForm{

  private static final long serialVersionUID = 1L;
  private List usersList=new ArrayList();
  private String users="";

  private String username="";
  
  private List userbooklist;
  private List sparelist;

  public String getUsers() {
    return users;
  }

  public void setUsers(String users) {
    this.users = users;
  }

  public List getUsersList() {
    return usersList;
  }

  public void setUsersList(List usersList) {
    this.usersList = usersList;
  }
  
  public List getUserbooklist() {
    return userbooklist;
  }

  public void setUserbooklist(List userbooklist) {
    this.userbooklist = userbooklist;
  }

  public List getSparelist() {
    return sparelist;
  }

  public void setSparelist(List sparelist) {
    this.sparelist = sparelist;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
