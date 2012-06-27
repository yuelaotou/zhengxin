package org.xpup.hafmis.sysloan.accounthandle.overpay.dto;

import java.math.BigDecimal;

public class OverPayTaDTO {
  private String loankouacc="";//贷款账号
  private String contractId="";//合同编号
  private String borrowerName="";//借款人姓名
  private String cardKind="";//证件类型
  private String temp_cardKind="";
  private String cardNum="";//证件号码
  private String loanMode="";//还款方式
  private String temp_loanMode="";
  private BigDecimal ovaerLoanRepay=new BigDecimal(0.00);//挂账余额
  private BigDecimal overpayMoney=new BigDecimal(0.00);//挂账金额
  private String loanBankId="";//放款银行
  private String officecode="";//办事处
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public BigDecimal getOverpayMoney() {
    return overpayMoney;
  }
  public void setOverpayMoney(BigDecimal overpayMoney) {
    this.overpayMoney = overpayMoney;
  }
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
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getTemp_cardKind() {
    return temp_cardKind;
  }
  public void setTemp_cardKind(String temp_cardKind) {
    this.temp_cardKind = temp_cardKind;
  }
  public String getTemp_loanMode() {
    return temp_loanMode;
  }
  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }
}
