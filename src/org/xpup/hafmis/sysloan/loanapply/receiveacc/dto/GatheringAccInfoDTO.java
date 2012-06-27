package org.xpup.hafmis.sysloan.loanapply.receiveacc.dto;

public class GatheringAccInfoDTO {

  // 合同编号
  private String contractId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";

  // 扣款银行id
  private String loanBankId = "";

  // 原扣款帐号
  private String oldBankAcc = "";

  // 新扣款帐号
  private String newBankAcc = "";

  // 操作員
  private String oprator = "";

  // pl130主鍵
  private String receiveBankModifyId = "";

  // 操作员真实姓名
  private String name = "";

  // 扣款账号修改日期

  private String modifyDate = "";

  public String getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getNewBankAcc() {
    return newBankAcc;
  }

  public void setNewBankAcc(String newBankAcc) {
    this.newBankAcc = newBankAcc;
  }

  public String getOldBankAcc() {
    return oldBankAcc;
  }

  public void setOldBankAcc(String oldBankAcc) {
    this.oldBankAcc = oldBankAcc;
  }

  public String getOprator() {
    return oprator;
  }

  public void setOprator(String oprator) {
    this.oprator = oprator;
  }

  public String getReceiveBankModifyId() {
    return receiveBankModifyId;
  }

  public void setReceiveBankModifyId(String receiveBankModifyId) {
    this.receiveBankModifyId = receiveBankModifyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
