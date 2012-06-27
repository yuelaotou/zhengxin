package org.xpup.hafmis.demo.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class DemoImportDTO extends impDto{
  private String id="";
  private String name="";
  private String idcard="";
  private String birthday="";
  private String sex="";
  private String salary="";
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
  
  


}
