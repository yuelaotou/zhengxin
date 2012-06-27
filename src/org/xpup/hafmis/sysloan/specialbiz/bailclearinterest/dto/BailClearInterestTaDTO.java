package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestTaDTO {

  private String loanBankName = null; // 放款银行

  private String paramExplainInterestD = null; // 定期利率

  private String paramExplainInterestL = null; // 活期利率

  private String loanKouAcc = null; // 贷款帐号

  private String contractId = null; // 合同ID

  private String borrowerName = null; // 借款人姓名

  private String cardNum = null; // 证件号码

  private String bailBalance = null;// 结息前保证金
  
  private String preIntegral = null;// 往年积数
  
  private String curIntegral = null;// 本年积数

  public String getCurIntegral() {
    return curIntegral;
  }

  public void setCurIntegral(String curIntegral) {
    this.curIntegral = curIntegral;
  }

  public String getPreIntegral() {
    return preIntegral;
  }

  public void setPreIntegral(String preIntegral) {
    this.preIntegral = preIntegral;
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

  public String getParamExplainInterestD() {
    return paramExplainInterestD;
  }

  public void setParamExplainInterestD(String paramExplainInterestD) {
    this.paramExplainInterestD = paramExplainInterestD;
  }

  public String getParamExplainInterestL() {
    return paramExplainInterestL;
  }

  public void setParamExplainInterestL(String paramExplainInterestL) {
    this.paramExplainInterestL = paramExplainInterestL;
  }

}
