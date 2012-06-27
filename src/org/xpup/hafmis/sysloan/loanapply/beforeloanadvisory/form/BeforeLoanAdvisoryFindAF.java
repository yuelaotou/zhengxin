package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form;

import org.apache.struts.action.ActionForm;

public class BeforeLoanAdvisoryFindAF  extends ActionForm{
  
/**
   * 
   */

private String empid="";
private String empName="";
private String empCardNum="";
private String orgid="";
private String orgName="";

public void reset()
{
  this.empid="";
  this.empName="";
  this.empCardNum="";
  this.orgid="";
  this.orgName="";
}
public String getEmpCardNum() {
  return empCardNum;
}
public void setEmpCardNum(String empCardNum) {
  this.empCardNum = empCardNum;
}
public String getEmpid() {
  return empid;
}
public void setEmpid(String empid) {
  this.empid = empid;
}
public String getEmpName() {
  return empName;
}
public void setEmpName(String empName) {
  this.empName = empName;
}
public String getOrgid() {
  return orgid;
}
public void setOrgid(String orgid) {
  this.orgid = orgid;
}
public String getOrgName() {
  return orgName;
}
public void setOrgName(String orgName) {
  this.orgName = orgName;
}

}
