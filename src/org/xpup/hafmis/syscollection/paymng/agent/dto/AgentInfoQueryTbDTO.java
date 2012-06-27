package org.xpup.hafmis.syscollection.paymng.agent.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class AgentInfoQueryTbDTO extends impDto {

  private static final long serialVersionUID = 1L;

  private String payId = "";
  private String payMonth = "";
  private String orgId = "";
  private String orgName = "";
  private String orgAgentNum = "";
  private String orgRealPaySum = "";
  private String empRealPaySum = "";
  
  public String getEmpRealPaySum() {
    return empRealPaySum;
  }
  public void setEmpRealPaySum(String empRealPaySum) {
    this.empRealPaySum = empRealPaySum;
  }
  public String getOrgAgentNum() {
    return orgAgentNum;
  }
  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
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
