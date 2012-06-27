package org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto;

public class BailpickupTaDTO {

  private String loanKouAcc = "";//贷款账号
  private String contractId = "";//合同编号
  private String borrowerName = "";//借款人姓名
  private String cardNum = "";//证件号码
  private String bailBalance = "";//保证金余额
  private String pickUpInterest = "";//提取利息
  private String pickSumMoney = "";//提取总金额
  private String overplusLoanMoney = "";//贷款余额
  private String noBackMoney = "";//呆账未收回金额
  private String ovaerLoanRepay = "";//挂账余额
  
  private String type = "";//隐藏 判断是否可以提取
  private String isDisabled = "";//确定按钮禁用
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getIsDisabled() {
    return isDisabled;
  }
  public void setIsDisabled(String isDisabled) {
    this.isDisabled = isDisabled;
  }
  public String getBailBalance() {
    return bailBalance;
  }
  public void setBailBalance(String bailBalance) {
    this.bailBalance = bailBalance;
  }
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
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getNoBackMoney() {
    return noBackMoney;
  }
  public void setNoBackMoney(String noBackMoney) {
    this.noBackMoney = noBackMoney;
  }
  public String getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(String ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(String overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getPickSumMoney() {
    return pickSumMoney;
  }
  public void setPickSumMoney(String pickSumMoney) {
    this.pickSumMoney = pickSumMoney;
  }
  public String getPickUpInterest() {
    return pickUpInterest;
  }
  public void setPickUpInterest(String pickUpInterest) {
    this.pickUpInterest = pickUpInterest;
  }
  
}
