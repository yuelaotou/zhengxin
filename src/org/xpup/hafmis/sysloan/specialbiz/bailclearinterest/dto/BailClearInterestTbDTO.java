package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto;

/**
 * @author 王野 2007-10-08
 */
public class BailClearInterestTbDTO {

  private String bizYear = null; // 结息年度
  
  private String loanBankName = null; // 放款银行

  private String loanKouAcc = null; // PL203 LOAN_KOU_ACC 贷款帐号

  private String borrowerName = null; // 借款人姓名

  private String bailBalance = null;// PL111 BAIL_BALANCE 结息前保证金
  
  private String occurMoney = null;// PL203 OCCUR_MONEY 结息利息  

  private String lastBalance = null;// 结息后保证金

  public String getBailBalance() {
    return bailBalance;
  }

  public void setBailBalance(String bailBalance) {
    this.bailBalance = bailBalance;
  }

  public String getBizYear() {
    return bizYear;
  }

  public void setBizYear(String bizYear) {
    this.bizYear = bizYear;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getLastBalance() {
    return lastBalance;
  }

  public void setLastBalance(String lastBalance) {
    this.lastBalance = lastBalance;
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

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }
  
  
}
