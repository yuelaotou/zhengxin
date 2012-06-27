package org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto;

import java.math.BigDecimal;

public class SpecialInfoChgDTO {
  private String contractId="";//合同编号
  private String borrowerName="";//借款人姓名
  private BigDecimal loanMoney=new BigDecimal(0.00);//贷款金额
  private String loanTimeLimit="";//贷款期限
  private BigDecimal loanMonthRate=new BigDecimal(0.00);//每月利率
  private String loanMode="";//还款方式
  private String temp_loanMode="";
  private BigDecimal corpusInterest=new BigDecimal(0.00);//月还本息
  private BigDecimal firstLoanMoney=new BigDecimal(0.00);//首次还款金额
  private BigDecimal loanPoundage=new BigDecimal(0.00);//手续费率
  private String aheadReturnAfter="";//1、提前还款后
  private String partReturnMonthLT="";//2、还款时间小于多少月
  private String isPartAheadReturn="";//2、是否允许部分提前还款
  private String allReturnMonthLT="";//3、还款时间小于多少月
  private String isAllReturn="";//3、是否允许一次性结清还款
  private BigDecimal leastReturnMoney=new BigDecimal(0.00);//4、最低还款金额
  private String mostAheadReturnYearTimes="";//5、年度内最多允许提前还款次数
  private String mostAheadReturnTimes="";//6、贷款期限内最多允许提前还款次数
  private String isAccept="";//7、提前还款是否收取手续费
  private String chargeMode="";//7、收费方式
  private BigDecimal aheadReturnMoneyPercent=new BigDecimal(0.00);//7、提前还款额百分比
  private BigDecimal money=new BigDecimal(0.00);//7、按额
  private String loanBankId="";//银行
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getAheadReturnAfter() {
    return aheadReturnAfter;
  }
  public void setAheadReturnAfter(String aheadReturnAfter) {
    this.aheadReturnAfter = aheadReturnAfter;
  }
  public BigDecimal getAheadReturnMoneyPercent() {
    return aheadReturnMoneyPercent;
  }
  public void setAheadReturnMoneyPercent(BigDecimal aheadReturnMoneyPercent) {
    this.aheadReturnMoneyPercent = aheadReturnMoneyPercent;
  }
  public String getAllReturnMonthLT() {
    return allReturnMonthLT;
  }
  public void setAllReturnMonthLT(String allReturnMonthLT) {
    this.allReturnMonthLT = allReturnMonthLT;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getChargeMode() {
    return chargeMode;
  }
  public void setChargeMode(String chargeMode) {
    this.chargeMode = chargeMode;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }
  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
  }
  public String getIsAccept() {
    return isAccept;
  }
  public void setIsAccept(String isAccept) {
    this.isAccept = isAccept;
  }
  public String getIsAllReturn() {
    return isAllReturn;
  }
  public void setIsAllReturn(String isAllReturn) {
    this.isAllReturn = isAllReturn;
  }
  public String getIsPartAheadReturn() {
    return isPartAheadReturn;
  }
  public void setIsPartAheadReturn(String isPartAheadReturn) {
    this.isPartAheadReturn = isPartAheadReturn;
  }
  public BigDecimal getLeastReturnMoney() {
    return leastReturnMoney;
  }
  public void setLeastReturnMoney(BigDecimal leastReturnMoney) {
    this.leastReturnMoney = leastReturnMoney;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public BigDecimal getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }
  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }
  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
  public BigDecimal getLoanPoundage() {
    return loanPoundage;
  }
  public void setLoanPoundage(BigDecimal loanPoundage) {
    this.loanPoundage = loanPoundage;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getMostAheadReturnTimes() {
    return mostAheadReturnTimes;
  }
  public void setMostAheadReturnTimes(String mostAheadReturnTimes) {
    this.mostAheadReturnTimes = mostAheadReturnTimes;
  }
  public String getMostAheadReturnYearTimes() {
    return mostAheadReturnYearTimes;
  }
  public void setMostAheadReturnYearTimes(String mostAheadReturnYearTimes) {
    this.mostAheadReturnYearTimes = mostAheadReturnYearTimes;
  }
  public String getPartReturnMonthLT() {
    return partReturnMonthLT;
  }
  public void setPartReturnMonthLT(String partReturnMonthLT) {
    this.partReturnMonthLT = partReturnMonthLT;
  }
  public String getTemp_loanMode() {
    return temp_loanMode;
  }
  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }
}
