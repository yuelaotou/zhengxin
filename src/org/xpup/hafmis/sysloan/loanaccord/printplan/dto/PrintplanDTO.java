package org.xpup.hafmis.sysloan.loanaccord.printplan.dto;

import java.math.BigDecimal;

public class PrintplanDTO {
  private String contractId = ""; // 合同编号

  private String borrowerName = ""; // 借款人姓名

  private String cardKind = ""; // 证件类型

  private String cardKindName = ""; // 显示证件类型对应的名称

  private String cardNum = ""; // 证件号码

  private String loanBankId = ""; // 放款银行

  private String loanBankName = ""; // 放款银行名称

  private BigDecimal loanMoney = new BigDecimal(0.00); // 借款金额

  private String loanTimeLimit = ""; // 贷款期限

  private String loanMode = ""; // 还款方式

  private String loanModeName = ""; // 还款方式名称

  private String loanKouAcc = "";// 银行收款账号(贷款账号)

  private BigDecimal overplusLoanMoney = new BigDecimal(0.00); //剩余金额
  
  private String overplusLimite="";    //剩余期限
  
  private String loanRepayDay="";      //还款日
  
  private String contractSt="";//合同状态
  
//贷款发放日期
  private String loanStartDate="";
  

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
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

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
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

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOverplusLimite() {
    return overplusLimite;
  }

  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

}
