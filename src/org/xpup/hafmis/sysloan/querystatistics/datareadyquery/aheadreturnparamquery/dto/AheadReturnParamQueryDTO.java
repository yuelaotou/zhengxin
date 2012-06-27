package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.dto;

import java.math.BigDecimal;

public class AheadReturnParamQueryDTO {
  private String loanBankId="";//银行
  private String temp_loanBankId="";
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
  private String row[]=new String[7];//复选框
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
  public String getChargeMode() {
    return chargeMode;
  }
  public void setChargeMode(String chargeMode) {
    this.chargeMode = chargeMode;
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
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
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
  public String[] getRow() {
    return row;
  }
  public void setRow(String[] row) {
    this.row = row;
  }
  public String getTemp_loanBankId() {
    return temp_loanBankId;
  }
  public void setTemp_loanBankId(String temp_loanBankId) {
    this.temp_loanBankId = temp_loanBankId;
  }
}
