package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto;

import java.math.BigDecimal;

public class OverdueInfoDTO {

  private String contractId = "";

  private String borrowerName = "";

  private String borrowerOrgName = "";

  private String loanKouAcc = "";

  private String borrowerTel = "";

  private String borrowerMobile = "";

  private String houseAddr = "";

  private String loanStartDate = "";
  
  private String borrowerOrgTel = "";

  private String astBorrowerName = "";

  private String astBorrowerOrgName = "";

  private String astBorrowerMobile = "";

  private BigDecimal loanMoney = new BigDecimal(0.00);

  private BigDecimal balance = new BigDecimal(0.00);

  private String repayMonth = "";

  private String bankId;

  private String overdueMonths;

  private String loanRate;
  
  private String loanBank;

  private BigDecimal corpus = new BigDecimal(0.00);

  private BigDecimal interest = new BigDecimal(0.00);

  private BigDecimal overdueMoney = new BigDecimal(0.00);

  private BigDecimal punishInterest = new BigDecimal(0.00);

  private BigDecimal shouldPayMoney = new BigDecimal(0.00);

  public String getAstBorrowerMobile() {
    return astBorrowerMobile;
  }

  public void setAstBorrowerMobile(String astBorrowerMobile) {
    this.astBorrowerMobile = astBorrowerMobile;
  }

  public String getAstBorrowerName() {
    return astBorrowerName;
  }

  public void setAstBorrowerName(String astBorrowerName) {
    this.astBorrowerName = astBorrowerName;
  }

  public String getAstBorrowerOrgName() {
    return astBorrowerOrgName;
  }

  public void setAstBorrowerOrgName(String astBorrowerOrgName) {
    this.astBorrowerOrgName = astBorrowerOrgName;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getBorrowerMobile() {
    return borrowerMobile;
  }

  public void setBorrowerMobile(String borrowerMobile) {
    this.borrowerMobile = borrowerMobile;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getBorrowerOrgName() {
    return borrowerOrgName;
  }

  public void setBorrowerOrgName(String borrowerOrgName) {
    this.borrowerOrgName = borrowerOrgName;
  }

  public String getBorrowerOrgTel() {
    return borrowerOrgTel;
  }

  public void setBorrowerOrgTel(String borrowerOrgTel) {
    this.borrowerOrgTel = borrowerOrgTel;
  }

  public String getBorrowerTel() {
    return borrowerTel;
  }

  public void setBorrowerTel(String borrowerTel) {
    this.borrowerTel = borrowerTel;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public BigDecimal getOverdueMoney() {
    return overdueMoney;
  }

  public void setOverdueMoney(BigDecimal overdueMoney) {
    this.overdueMoney = overdueMoney;
  }

  public BigDecimal getShouldPayMoney() {
    return shouldPayMoney;
  }

  public void setShouldPayMoney(BigDecimal shouldPayMoney) {
    this.shouldPayMoney = shouldPayMoney;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getOverdueMonths() {
    return overdueMonths;
  }

  public void setOverdueMonths(String overdueMonths) {
    this.overdueMonths = overdueMonths;
  }

  public String getLoanRate() {
    return loanRate;
  }

  public void setLoanRate(String loanRate) {
    this.loanRate = loanRate;
  }

  public String getRepayMonth() {
    return repayMonth;
  }

  public void setRepayMonth(String repayMonth) {
    this.repayMonth = repayMonth;
  }

  public BigDecimal getCorpus() {
    return corpus;
  }

  public void setCorpus(BigDecimal corpus) {
    this.corpus = corpus;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

}
