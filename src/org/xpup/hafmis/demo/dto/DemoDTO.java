package org.xpup.hafmis.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class DemoDTO implements Serializable{

  private static final long serialVersionUID = 0L;

  private int id;

  private String name;

  private String idcard;

  private String birthday="";
  
  private BigDecimal salary=new BigDecimal(0.00);
  
  private BigDecimal  balance=new BigDecimal(0.00);;
  
  private String sex;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
  
  
}
