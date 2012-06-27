package org.xpup.hafmis.syscollection.peoplebank.documents.dto;

public class DocumentstopDTO {
  
  private String num;//数据格式版本号
  private String uporganization;//数据上报机构
  private String people;//联系人
  private String tep;//联系电话
  private String add;//发生地点
  private String organization;//数据发生机构

  private String settingtype;//分隔类型

  private String parachar;//分隔符号
  private String paravalue;//分隔参数
  private String paravalue_1;//分隔参数1
  private String paravalue_2;//分隔参数2
  private String paravalue_3;//分隔参数3
  
  public String getAdd() {
    return add;
  }
  public String getParachar() {
    return parachar;
  }
  public void setParachar(String parachar) {
    this.parachar = parachar;
  }
  public String getParavalue_1() {
    return paravalue_1;
  }
  public void setParavalue_1(String paravalue_1) {
    this.paravalue_1 = paravalue_1;
  }
  public String getParavalue_2() {
    return paravalue_2;
  }
  public void setParavalue_2(String paravalue_2) {
    this.paravalue_2 = paravalue_2;
  }
  public String getParavalue_3() {
    return paravalue_3;
  }
  public void setParavalue_3(String paravalue_3) {
    this.paravalue_3 = paravalue_3;
  }
  public void setAdd(String add) {
    this.add = add;
  }
  public String getNum() {
    return num;
  }
  public void setNum(String num) {
    this.num = num;
  }
  public String getOrganization() {
    return organization;
  }
  public void setOrganization(String organization) {
    this.organization = organization;
  }
  public String getPeople() {
    return people;
  }
  public void setPeople(String people) {
    this.people = people;
  }
  public String getTep() {
    return tep;
  }
  public void setTep(String tep) {
    this.tep = tep;
  }
  public String getUporganization() {
    return uporganization;
  }
  public void setUporganization(String uporganization) {
    this.uporganization = uporganization;
  }
  public String getSettingtype() {
    return settingtype;
  }
  public void setSettingtype(String settingtype) {
    this.settingtype = settingtype;
  }
  public String getParavalue() {
    return paravalue;
  }
  public void setParavalue(String paravalue) {
    this.paravalue = paravalue;
  }

}
