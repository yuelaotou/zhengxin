package org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto;

import java.math.BigDecimal;

public class LoanConditionsSetDTO {
 private String office="";//办事处
 private String isRegular="";//1、公积金个人账户状态是否正常
 private String one="";//1、是否启用
 private String chgbizMonth="";//2、公积金连续汇缴月数大于多少月
 private String two="";//2、是否启用
 private String accountOpenMonth="";//3、公积开户时间大于多少月
 private String three="";//3、是否启用
 private String maleAge="";//4、贷款人实际年龄加贷款期限不超过多少岁(男)
 private String femaleAge="";//4、贷款人实际年龄加贷款期限不超过多少岁(女)
 private String four="";//4、是否启用
 private String loanLimitMin="";//5、贷款期限不低于多少月
 private String loanLimitMax="";//5、最高期限不超过多少月
 private String five="";//5、是否启用
 private String overTimeMax="";//6、信用情况,曾逾期不超过多少月
 private String six="";//6、是否启用
 private BigDecimal loanMoneyMax=new BigDecimal(0.00);//7、单方拥有公积金贷款金额不能超过多少元
 private BigDecimal otherLoanMoneyMax=new BigDecimal(0.00);//7、有辅助贷款人的贷款金额不能超过多少元
 private String seven="";//7、是否启用
 private BigDecimal merchandiseRateMax=new BigDecimal(0.00);//8、贷款金额不能超过商品房价的百分比
 private String eight="";//8、是否启用
 private BigDecimal secondhandRateMax=new BigDecimal(0.00);//9、贷款金额不能超过二手房价的百分比
 private String nine="";//9、是否启用
 private BigDecimal merchandiseMoneyMax=new BigDecimal(0.00);//10、商品房贷款最高金额不超过多少元
 private String ten="";//10、是否启用
 private BigDecimal secondhandMoneyMax=new BigDecimal(0.00);//11、二手房贷款最高金额不超过多少元
 private String eleven="";//11、是否启用
 
 //新增开始-->
 private String qianJiaoMonth="";//2.2公积金欠缴月数
 private BigDecimal secondhandRateMax_1=new BigDecimal(0.00);//9.1、贷款金额不能超过二手房价的百分比（5--10年）
 private BigDecimal secondhandRateMax_2=new BigDecimal(0.00);//9.2、贷款金额不能超过二手房价的百分比（11年以上）
 private BigDecimal beiShu=new BigDecimal(0.00);//12不得超过贷款家庭成员退休年龄内所交纳住房公积金数额 
 private String timeMax_1="";//13.1、贷款最高年限商品房
 private String timeMax_2="";//13.1、贷款最高年限二手房
 private String comNature_1="";//15.1单位性质一
 private String comNature_2="";//16.1单位性质二
 private String comNature_3="";//17.1单位性质三
 private String personCount_1="";//15.2单位性质一的人数
 private String personCount_2="";//16.2单位性质二的人数
 private String personCount_3="";//17.2单位性质三的人数
 private String monthCount_1="";//15.3单位性质一的月数
 private String monthCount_2="";//16.3单位性质二的月数
 private String monthCount_3="";//17.3单位性质三的月数
 private BigDecimal  salaryRate =new BigDecimal(0.00);//16、借款人月收入还款比例
 private String twive="";//12、是否启用
 private String thirteen="";//13、是否启用
 private String fourteen="";//14、是否启用
 private String fifteen="";//15、是否启用
 private String sixteen="";//16、是否启用
 private String seventeen="";//17、是否启用
 private String eighteen="";//18、是否启用
 private String ninteen="";//19、是否启用
 //<--新增结束
public String getAccountOpenMonth() {
  return accountOpenMonth;
}
public void setAccountOpenMonth(String accountOpenMonth) {
  this.accountOpenMonth = accountOpenMonth;
}
public String getChgbizMonth() {
  return chgbizMonth;
}
public void setChgbizMonth(String chgbizMonth) {
  this.chgbizMonth = chgbizMonth;
}
public String getEight() {
  return eight;
}
public void setEight(String eight) {
  this.eight = eight;
}
public String getEleven() {
  return eleven;
}
public void setEleven(String eleven) {
  this.eleven = eleven;
}
public String getFemaleAge() {
  return femaleAge;
}
public void setFemaleAge(String femaleAge) {
  this.femaleAge = femaleAge;
}
public String getFive() {
  return five;
}
public void setFive(String five) {
  this.five = five;
}
public String getFour() {
  return four;
}
public void setFour(String four) {
  this.four = four;
}
public String getIsRegular() {
  return isRegular;
}
public void setIsRegular(String isRegular) {
  this.isRegular = isRegular;
}
public String getLoanLimitMax() {
  return loanLimitMax;
}
public void setLoanLimitMax(String loanLimitMax) {
  this.loanLimitMax = loanLimitMax;
}
public String getLoanLimitMin() {
  return loanLimitMin;
}
public void setLoanLimitMin(String loanLimitMin) {
  this.loanLimitMin = loanLimitMin;
}
public BigDecimal getLoanMoneyMax() {
  return loanMoneyMax;
}
public void setLoanMoneyMax(BigDecimal loanMoneyMax) {
  this.loanMoneyMax = loanMoneyMax;
}
public String getMaleAge() {
  return maleAge;
}
public void setMaleAge(String maleAge) {
  this.maleAge = maleAge;
}
public BigDecimal getMerchandiseMoneyMax() {
  return merchandiseMoneyMax;
}
public void setMerchandiseMoneyMax(BigDecimal merchandiseMoneyMax) {
  this.merchandiseMoneyMax = merchandiseMoneyMax;
}
public BigDecimal getMerchandiseRateMax() {
  return merchandiseRateMax;
}
public void setMerchandiseRateMax(BigDecimal merchandiseRateMax) {
  this.merchandiseRateMax = merchandiseRateMax;
}
public String getNine() {
  return nine;
}
public void setNine(String nine) {
  this.nine = nine;
}
public String getOne() {
  return one;
}
public void setOne(String one) {
  this.one = one;
}
public BigDecimal getOtherLoanMoneyMax() {
  return otherLoanMoneyMax;
}
public void setOtherLoanMoneyMax(BigDecimal otherLoanMoneyMax) {
  this.otherLoanMoneyMax = otherLoanMoneyMax;
}
public String getOverTimeMax() {
  return overTimeMax;
}
public void setOverTimeMax(String overTimeMax) {
  this.overTimeMax = overTimeMax;
}
public BigDecimal getSecondhandMoneyMax() {
  return secondhandMoneyMax;
}
public void setSecondhandMoneyMax(BigDecimal secondhandMoneyMax) {
  this.secondhandMoneyMax = secondhandMoneyMax;
}
public BigDecimal getSecondhandRateMax() {
  return secondhandRateMax;
}
public void setSecondhandRateMax(BigDecimal secondhandRateMax) {
  this.secondhandRateMax = secondhandRateMax;
}
public String getSeven() {
  return seven;
}
public void setSeven(String seven) {
  this.seven = seven;
}
public String getSix() {
  return six;
}
public void setSix(String six) {
  this.six = six;
}
public String getTen() {
  return ten;
}
public void setTen(String ten) {
  this.ten = ten;
}
public String getThree() {
  return three;
}
public void setThree(String three) {
  this.three = three;
}
public String getTwo() {
  return two;
}
public void setTwo(String two) {
  this.two = two;
}
public String getOffice() {
  return office;
}
public void setOffice(String office) {
  this.office = office;
}
public BigDecimal getBeiShu() {
  return beiShu;
}
public void setBeiShu(BigDecimal beiShu) {
  this.beiShu = beiShu;
}
public String getFourteen() {
  return fourteen;
}
public void setFourteen(String fourteen) {
  this.fourteen = fourteen;
}
public String getQianJiaoMonth() {
  return qianJiaoMonth;
}
public void setQianJiaoMonth(String qianJiaoMonth) {
  this.qianJiaoMonth = qianJiaoMonth;
}
public BigDecimal getSalaryRate() {
  return salaryRate;
}
public void setSalaryRate(BigDecimal salaryRate) {
  this.salaryRate = salaryRate;
}
public BigDecimal getSecondhandRateMax_1() {
  return secondhandRateMax_1;
}
public void setSecondhandRateMax_1(BigDecimal secondhandRateMax_1) {
  this.secondhandRateMax_1 = secondhandRateMax_1;
}
public BigDecimal getSecondhandRateMax_2() {
  return secondhandRateMax_2;
}
public void setSecondhandRateMax_2(BigDecimal secondhandRateMax_2) {
  this.secondhandRateMax_2 = secondhandRateMax_2;
}
public String getThirteen() {
  return thirteen;
}
public void setThirteen(String thirteen) {
  this.thirteen = thirteen;
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
public String getTwive() {
  return twive;
}
public void setTwive(String twive) {
  this.twive = twive;
}
public String getFifteen() {
  return fifteen;
}
public void setFifteen(String fifteen) {
  this.fifteen = fifteen;
}
public String getSixteen() {
  return sixteen;
}
public void setSixteen(String sixteen) {
  this.sixteen = sixteen;
}
public String getComNature_1() {
  return comNature_1;
}
public void setComNature_1(String comNature_1) {
  this.comNature_1 = comNature_1;
}
public String getComNature_2() {
  return comNature_2;
}
public void setComNature_2(String comNature_2) {
  this.comNature_2 = comNature_2;
}
public String getComNature_3() {
  return comNature_3;
}
public void setComNature_3(String comNature_3) {
  this.comNature_3 = comNature_3;
}
public String getEighteen() {
  return eighteen;
}
public void setEighteen(String eighteen) {
  this.eighteen = eighteen;
}
public String getMonthCount_1() {
  return monthCount_1;
}
public void setMonthCount_1(String monthCount_1) {
  this.monthCount_1 = monthCount_1;
}
public String getMonthCount_2() {
  return monthCount_2;
}
public void setMonthCount_2(String monthCount_2) {
  this.monthCount_2 = monthCount_2;
}
public String getMonthCount_3() {
  return monthCount_3;
}
public void setMonthCount_3(String monthCount_3) {
  this.monthCount_3 = monthCount_3;
}
public String getNinteen() {
  return ninteen;
}
public void setNinteen(String ninteen) {
  this.ninteen = ninteen;
}
public String getPersonCount_1() {
  return personCount_1;
}
public void setPersonCount_1(String personCount_1) {
  this.personCount_1 = personCount_1;
}
public String getPersonCount_2() {
  return personCount_2;
}
public void setPersonCount_2(String personCount_2) {
  this.personCount_2 = personCount_2;
}
public String getPersonCount_3() {
  return personCount_3;
}
public void setPersonCount_3(String personCount_3) {
  this.personCount_3 = personCount_3;
}
public String getSeventeen() {
  return seventeen;
}
public void setSeventeen(String seventeen) {
  this.seventeen = seventeen;
}
}
