package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;


public class ChgpersonExpDTO {

  
  /**
   职工编号、职工姓名、证件类型、证件号码、出生日期、性别、所在部门、工资基数、单位缴额、职工缴额、是否参与汇缴,变更类型
   */
  
   private String empcode="";
   private String  empname="";
   private String  cardkind="";
   private String  cardnum="";
   private String birthday="";
   private String sex="";
   private String dept="";
   private String salarybase="";
   private String orgpay="";
   private String emppay="";
   private String chgtype="";
   private String partin="";
   
//缴存方式
   private String paymode=null;
   
   private String orgunitcode = null;

   private String orgunitchgmonth = null;

   private String orgunitname = null;
   
   private String orgunitcodecontent = null;

   private String orgunitchgmonthcontent = null;

   private String orgunitnamecontent = null;
   
   /**
    * 职工缴存状态
    */
   private String payStatus = "";

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getCardkind() {
    return cardkind;
  }

  public void setCardkind(String cardkind) {
    this.cardkind = cardkind;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getChgtype() {
    return chgtype;
  }

  public void setChgtype(String chgtype) {
    this.chgtype = chgtype;
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public String getEmpcode() {
    return empcode;
  }

  public void setEmpcode(String empcode) {
    this.empcode = empcode;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public String getEmppay() {
    return emppay;
  }

  public void setEmppay(String emppay) {
    this.emppay = emppay;
  }

  public String getOrgpay() {
    return orgpay;
  }

  public void setOrgpay(String orgpay) {
    this.orgpay = orgpay;
  }

  public String getOrgunitchgmonth() {
    return orgunitchgmonth;
  }

  public void setOrgunitchgmonth(String orgunitchgmonth) {
    this.orgunitchgmonth = orgunitchgmonth;
  }

  public String getOrgunitchgmonthcontent() {
    return orgunitchgmonthcontent;
  }

  public void setOrgunitchgmonthcontent(String orgunitchgmonthcontent) {
    this.orgunitchgmonthcontent = orgunitchgmonthcontent;
  }

  public String getOrgunitcode() {
    return orgunitcode;
  }

  public void setOrgunitcode(String orgunitcode) {
    this.orgunitcode = orgunitcode;
  }

  public String getOrgunitcodecontent() {
    return orgunitcodecontent;
  }

  public void setOrgunitcodecontent(String orgunitcodecontent) {
    this.orgunitcodecontent = orgunitcodecontent;
  }

  public String getOrgunitname() {
    return orgunitname;
  }

  public void setOrgunitname(String orgunitname) {
    this.orgunitname = orgunitname;
  }

  public String getOrgunitnamecontent() {
    return orgunitnamecontent;
  }

  public void setOrgunitnamecontent(String orgunitnamecontent) {
    this.orgunitnamecontent = orgunitnamecontent;
  }

  public String getPartin() {
    return partin;
  }

  public void setPartin(String partin) {
    this.partin = partin;
  }

  public String getSalarybase() {
    return salarybase;
  }

  public void setSalarybase(String salarybase) {
    this.salarybase = salarybase;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPaymode() {
    return paymode;
  }

  public void setPaymode(String paymode) {
    this.paymode = paymode;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

}
