package org.xpup.hafmis.sysloan.loanapply.giveacc.dto;

public class GiveaccModifyDTO {
  //合同编号
  private String contractId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件类型
  private String cardKind = "";

  // 证件号码
  private String cardNum = "";

  // 原划款银行
  private String oldSellerPayBank = "";

  // 原划款银行帐号
  private String oldPayBankAcc = "";
  
  // 新划款银行帐号
  private String newPayBankAcc = "";
  
  // 新划款银行
  private String newSellerPayBank = "";

  // 售房人姓名
  private String sellerName = "";

  // 备注
  private String remark = "";

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
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

  public String getOldPayBankAcc() {
    return oldPayBankAcc;
  }

  public void setOldPayBankAcc(String oldPayBankAcc) {
    this.oldPayBankAcc = oldPayBankAcc;
  }

  public String getOldSellerPayBank() {
    return oldSellerPayBank;
  }

  public void setOldSellerPayBank(String oldSellerPayBank) {
    this.oldSellerPayBank = oldSellerPayBank;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public String getNewPayBankAcc() {
    return newPayBankAcc;
  }

  public void setNewPayBankAcc(String newPayBankAcc) {
    this.newPayBankAcc = newPayBankAcc;
  }

  public String getNewSellerPayBank() {
    return newSellerPayBank;
  }

  public void setNewSellerPayBank(String newSellerPayBank) {
    this.newSellerPayBank = newSellerPayBank;
  }
}
