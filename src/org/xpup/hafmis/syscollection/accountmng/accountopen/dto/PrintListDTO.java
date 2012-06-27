package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

public class PrintListDTO {
  private String orgid="";
  private String orgname="";
  private String empid="";
  private String empname="";
  private String cardnum="";
  private String salarybase="";
  private String orgpay="";
  private String emppay="";
  private String sumpay="";
  private String empagentnum="";
  private String opendate="";
  
  
  private String sex="";
  private String id_count="";
  private String ARTIFICIAL_PERSON="";
  private String TRANSACTOR_NAME="";
  
  public String getARTIFICIAL_PERSON() {
    return ARTIFICIAL_PERSON;
  }
  public void setARTIFICIAL_PERSON(String artificial_person) {
    ARTIFICIAL_PERSON = artificial_person;
  }
  public String getTRANSACTOR_NAME() {
    return TRANSACTOR_NAME;
  }
  public void setTRANSACTOR_NAME(String transactor_name) {
    TRANSACTOR_NAME = transactor_name;
  }
  public String getId_count() {
    return id_count;
  }
  public void setId_count(String id_count) {
    this.id_count = id_count;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getOpendate() {
    return opendate;
  }
  public void setOpendate(String opendate) {
    this.opendate = opendate;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getEmpagentnum() {
    return empagentnum;
  }
  public void setEmpagentnum(String empagentnum) {
    this.empagentnum = empagentnum;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
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
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public String getOrgpay() {
    return orgpay;
  }
  public void setOrgpay(String orgpay) {
    this.orgpay = orgpay;
  }
  public String getSalarybase() {
    return salarybase;
  }
  public void setSalarybase(String salarybase) {
    this.salarybase = salarybase;
  }
  public String getSumpay() {
    return sumpay;
  }
  public void setSumpay(String sumpay) {
    this.sumpay = sumpay;
  }
}
