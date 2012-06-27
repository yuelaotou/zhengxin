package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class PickMonthReportBankPopAF extends ActionForm{
  
  private String orgId = "";//单位编号
  
  private String orgName = "";//单位名称
  
  private String empId = "";//职工编号
  
  private String empName = "";//职工姓名
  
  private String pickReason = "";//提取原因
  
  private Map map = new HashMap();

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
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

  public String getPickReason() {
    return pickReason;
  }

  public void setPickReason(String pickReason) {
    this.pickReason = pickReason;
  }
}
