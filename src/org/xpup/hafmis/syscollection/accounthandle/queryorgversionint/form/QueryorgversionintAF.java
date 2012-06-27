package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.form;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class QueryorgversionintAF extends ActionForm{
  private String orgId = "";
  private String orgName = "";
  private String empId = "";
  private String empName = "";
  private String clearInterestType = "";
  private Map map = new HashMap();
  private List list = new ArrayList();
  
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
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getClearInterestType() {
    return clearInterestType;
  }
  public void setClearInterestType(String clearInterestType) {
    this.clearInterestType = clearInterestType;
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
