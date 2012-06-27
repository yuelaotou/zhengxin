package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form;

import org.apache.struts.action.ActionForm;

public class EmpAccountPrintPopAF extends ActionForm {
  String orgidst = "";

  String orgidend = "";

  String empidst = "";

  String empidend = "";

  String timeSt = "";

  String timeEnd = "";

  public String getEmpidend() {
    return empidend;
  }

  public void setEmpidend(String empidend) {
    this.empidend = empidend;
  }

  public String getEmpidst() {
    return empidst;
  }

  public void setEmpidst(String empidst) {
    this.empidst = empidst;
  }

  public String getOrgidend() {
    return orgidend;
  }

  public void setOrgidend(String orgidend) {
    this.orgidend = orgidend;
  }

  public String getOrgidst() {
    return orgidst;
  }

  public void setOrgidst(String orgidst) {
    this.orgidst = orgidst;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }

  public String getTimeSt() {
    return timeSt;
  }

  public void setTimeSt(String timeSt) {
    this.timeSt = timeSt;
  }
}
