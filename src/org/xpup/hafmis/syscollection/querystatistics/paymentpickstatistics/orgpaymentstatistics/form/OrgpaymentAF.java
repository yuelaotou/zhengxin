package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.common.form.IdAF;

public class OrgpaymentAF extends IdAF{
  
  
  private String orgId = null;//单位编号
  
  private String orgName = null;//单位名称
  
  private String empId = null;//职工编号
  
  private String empName = null;//职工姓名
  
  private String pay_month = null;//缴存月份(查询年份)
  
  private String orgPayment = null;//单位缴交
  
  private String empPayment = null;//职工缴交
  
  private List list = new ArrayList();
  
  private List totallist = new ArrayList();
  
  private List yearlist = new ArrayList();


  public List getYearlist() {
    return yearlist;
  }


  public void setYearlist(List yearlist) {
    this.yearlist = yearlist;
  }


  public List getTotallist() {
    return totallist;
  }


  public void setTotallist(List totallist) {
    this.totallist = totallist;
  }


  public String getEmpPayment() {
    return empPayment;
  }


  public void setEmpPayment(String empPayment) {
    this.empPayment = empPayment;
  }


  public List getList() {
    return list;
  }


  public void setList(List list) {
    this.list = list;
  }


  public String getOrgId() {
    return orgId;
  }


  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  

  public String getOrgName() {
    return orgName;
  }


  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }


  public String getOrgPayment() {
    return orgPayment;
  }


  public void setOrgPayment(String orgPayment) {
    this.orgPayment = orgPayment;
  }


  public String getPay_month() {
    return pay_month;
  }


  public void setPay_month(String pay_month) {
    this.pay_month = pay_month;
  }


  public String getEmpId() {
    return empId;
  }


  public void setEmpId(String empId) {
    this.empId = empId;
  }


  public String getEmpName() {
    return empName;
  }


  public void setEmpName(String empName) {
    this.empName = empName;
  }
  

 
}
