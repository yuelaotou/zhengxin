package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserAF extends ActionForm{

  
  private String User="";
  private String username;  
  private List userList;

  public String getUser() {
    return User;
  }

  public void setUser(String user) {
    User = user;
  }

  public List getUserList() {
    return userList;
  }

  public void setUserList(List userList) {
    this.userList = userList;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  
   
  

}
