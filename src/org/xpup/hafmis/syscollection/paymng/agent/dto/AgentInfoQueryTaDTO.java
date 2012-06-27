package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.io.Serializable;

public class AgentInfoQueryTaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String payId = "";
  private String noteNum = "";
  private String payMonth = "";
  private String orgIdSum = "";
  private String empIdSum = "";
  private String orgRealPaySum = "";
  private String empRealPaySum = "";
  private String payMode = "";
  
  public String getPayMode() {
    return payMode;
  }
  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
  public String getEmpIdSum() {
    return empIdSum;
  }
  public void setEmpIdSum(String empIdSum) {
    this.empIdSum = empIdSum;
  }
  public String getEmpRealPaySum() {
    return empRealPaySum;
  }
  public void setEmpRealPaySum(String empRealPaySum) {
    this.empRealPaySum = empRealPaySum;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public String getOrgIdSum() {
    return orgIdSum;
  }
  public void setOrgIdSum(String orgIdSum) {
    this.orgIdSum = orgIdSum;
  }
  public String getOrgRealPaySum() {
    return orgRealPaySum;
  }
  public void setOrgRealPaySum(String orgRealPaySum) {
    this.orgRealPaySum = orgRealPaySum;
  }
  public String getPayId() {
    return payId;
  }
  public void setPayId(String payId) {
    this.payId = payId;
  }
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }
}
