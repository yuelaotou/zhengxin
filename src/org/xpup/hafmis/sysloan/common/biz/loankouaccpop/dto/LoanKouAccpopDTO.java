package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.dto;

public class LoanKouAccpopDTO {
  private String loankouacc = "";//贷款账号
  private String contractId = ""; //合同ID
  private String borrowerName = "";  //BORROWER_NAME 借款人姓名
  private String cardNum = "";  //证件号码
  private String empId = ""; //职工ID
  private String orgId = "";//单位ID
  private String orgName = "";//单位名称
  private String contractSt = "";//合同状态
  
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getContractSt() {
    return contractSt;
  }
  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
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
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
}
