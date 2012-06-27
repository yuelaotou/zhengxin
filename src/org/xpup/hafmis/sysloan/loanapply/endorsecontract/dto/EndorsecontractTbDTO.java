package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

public class EndorsecontractTbDTO {


  private String pledgePerson = "";//抵押人姓名
  private String office = "";//抵押权人（即××中心）
  private String pledgeMatterName = "";//抵押物名称
  private String pledgeAddr = "";//抵押物地址
  private String pledgeValue = "";//抵押值
  private String evaluateValue = "";//评估值
  private String status = "";//抵押合同状态
  
  public String getEvaluateValue() {
    return evaluateValue;
  }
  public void setEvaluateValue(String evaluateValue) {
    this.evaluateValue = evaluateValue;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getPledgeAddr() {
    return pledgeAddr;
  }
  public void setPledgeAddr(String pledgeAddr) {
    this.pledgeAddr = pledgeAddr;
  }
  public String getPledgeMatterName() {
    return pledgeMatterName;
  }
  public void setPledgeMatterName(String pledgeMatterName) {
    this.pledgeMatterName = pledgeMatterName;
  }
  public String getPledgePerson() {
    return pledgePerson;
  }
  public void setPledgePerson(String pledgePerson) {
    this.pledgePerson = pledgePerson;
  }
  public String getPledgeValue() {
    return pledgeValue;
  }
  public void setPledgeValue(String pledgeValue) {
    this.pledgeValue = pledgeValue;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  
  
  
}
