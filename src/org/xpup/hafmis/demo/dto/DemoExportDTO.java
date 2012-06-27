package org.xpup.hafmis.demo.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class DemoExportDTO implements ExpDto{

  private static final long serialVersionUID = 0L;

  private String id;

  private String name;

  private String idcard;

  private String birthday="";
  
  private String salary="";
  
  private String  balance="";
  
  private String sex;

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getInfo(String s) {
    if(s.equals("id"))
      return id;
    if(s.equals("name"))
        return name;
    if(s.equals("idcard"))
        return idcard;
    if(s.equals("birthday"))
      return birthday;
    if(s.equals("sex"))
      return sex;
    if(s.equals("salary"))
      return salary;
    else
        return null;
  }

  
}
