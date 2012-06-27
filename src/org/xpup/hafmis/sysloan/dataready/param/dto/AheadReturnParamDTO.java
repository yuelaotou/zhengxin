package org.xpup.hafmis.sysloan.dataready.param.dto;

import java.math.BigDecimal;

public class AheadReturnParamDTO {
  private String loanBankId="";//银行
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
  private String maleAge=""; //男人年龄
  private String femaleAge="";//女人年龄
  private String timeMax_1="";//商品房年限
  private String timeMax_2="";//二手房年限
  private String salaryRate="";//工资比例

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
  public String getFemaleAge() {
    return femaleAge;
  }
  public void setFemaleAge(String femaleAge) {
    this.femaleAge = femaleAge;
  }
  public String getMaleAge() {
    return maleAge;
  }
  public void setMaleAge(String maleAge) {
    this.maleAge = maleAge;
  }
  public String getSalaryRate() {
    return salaryRate;
  }
  public void setSalaryRate(String salaryRate) {
    this.salaryRate = salaryRate;
  }
  public String getTimeMax_1() {
    return timeMax_1;
  }
  public void setTimeMax_1(String timeMax_1) {
    this.timeMax_1 = timeMax_1;
  }
  public String getTimeMax_2() {
    return timeMax_2;
  }
  public void setTimeMax_2(String timeMax_2) {
    this.timeMax_2 = timeMax_2;
  }
}
