package org.xpup.hafmis.syscollection.paymng.agent.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class AgentInfoQueryTcDTO extends impDto {
  
  private static final long serialVersionUID = 1L;

  private String empId = "";
  private String empName = "";
  private String empAgentNum = "";
  private String cardNum = "";
  private String orgRealPay = "";
  private String empRealPay = "";
  
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getEmpAgentNum() {
    return empAgentNum;
  }
  public void setEmpAgentNum(String empAgentNum) {
    this.empAgentNum = empAgentNum;
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
  public String getEmpRealPay() {
    return empRealPay;
  }
  public void setEmpRealPay(String empRealPay) {
    this.empRealPay = empRealPay;
  }
  public String getOrgRealPay() {
    return orgRealPay;
  }
  public void setOrgRealPay(String orgRealPay) {
    this.orgRealPay = orgRealPay;
  }
}
