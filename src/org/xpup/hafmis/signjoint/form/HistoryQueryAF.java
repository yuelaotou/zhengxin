package org.xpup.hafmis.signjoint.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

public class HistoryQueryAF extends ValidatorActionForm{

  private String orgid;
  private String orgname;
  private String empid;
  private String empname;
  private String transactdatastart;
  private String transactdataend;
  private String affirmdatastart;
  private String affirmdataend;
  private String isucceed;
  private List list=new ArrayList();
  
  
  public HistoryQueryAF(String orgid, String orgname, String empid, String empname, String transactdatastart, String transactdataend, String affirmdatastart, String affirmdataend,String isucceed) {
    this.orgid = orgid;
    this.orgname = orgname;
    this.empid = empid;
    this.empname = empname;
    this.transactdatastart = transactdatastart;
    this.transactdataend = transactdataend;
    this.affirmdatastart = affirmdatastart;
    this.affirmdataend = affirmdataend;
    this.isucceed=isucceed;
  }
  
  public HistoryQueryAF() {
    this.orgid = "";
    this.orgname = "";
    this.empid = "";
    this.empname = "";
    this.transactdatastart = "";
    this.transactdataend = "";
    this.affirmdatastart = "";
    this.affirmdataend = "";
    this.isucceed="";
  }
  
  public String getAffirmdataend() {
    return affirmdataend;
  }
  public void setAffirmdataend(String affirmdataend) {
    this.affirmdataend = affirmdataend;
  }
  public String getAffirmdatastart() {
    return affirmdatastart;
  }
  public void setAffirmdatastart(String affirmdatastart) {
    this.affirmdatastart = affirmdatastart;
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
  public String getTransactdataend() {
    return transactdataend;
  }
  public void setTransactdataend(String transactdataend) {
    this.transactdataend = transactdataend;
  }
  public String getTransactdatastart() {
    return transactdatastart;
  }
  public void setTransactdatastart(String transactdatastart) {
    this.transactdatastart = transactdatastart;
  }

  public String getIsucceed() {
    return isucceed;
  }

  public void setIsucceed(String isucceed) {
    this.isucceed = isucceed;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }


  
}
