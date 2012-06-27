package org.xpup.hafmis.sysloan.loanapply.giveacc.dto;

public class HouseListDTO {

  // 合同编号
  private String contractId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";

  // 售房人姓名
  private String sellerName = "";

  // 备注
  private String remark = "";

  // 新划款银行
  private String newSellerPayBank = "";

  // 新划款银行帐号
  private String newPayBankAcc = "";

  // 修改人
  private String operator = "";

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

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
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

}
