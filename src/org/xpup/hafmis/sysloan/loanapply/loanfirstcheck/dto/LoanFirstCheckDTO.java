package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.dto;

public class LoanFirstCheckDTO {
  
  private String contractId;
  
  private String borrowerName;
  
  private String cardNum;
  
  private String orgName;
  
  private String housePrice;
  
  private String loanMoney;
  
  private String loanTimeLimit;
  
  private String houseArea;
  
  private String houseAdd;
  
  private String houseType = "";// 购房类型
  
  private String operator="";//操作员

  private String contractSt = "";// 合同状态

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

  public String getHouseAdd() {
    return houseAdd;
  }

  public void setHouseAdd(String houseAdd) {
    this.houseAdd = houseAdd;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHousePrice() {
    return housePrice;
  }

  public void setHousePrice(String housePrice) {
    this.housePrice = housePrice;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  
  
}
