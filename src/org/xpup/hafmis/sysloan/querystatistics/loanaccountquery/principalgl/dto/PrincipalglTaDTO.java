package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.dto;
/**
 * 
 * @author yuqf
 *2007-10-19
 */
public class PrincipalglTaDTO {

  private String id = "";
  
  private String office = "";//办事处
  private String loanBank = "";//放款银行
  private String developerName = "";//开发商
  private String floorNum = "";//楼盘
  private String assistantOrgName = "";//担保公司
  private String happenYear = "";//发生年份
  private String begPerDebit = "";//期初累计借方发生额
  private String begPerLender = "";//期初累计贷方发生金额
  private String begPerDebitDegree = "";//期初累计借方发生次数
  private String begPerLenderDegree = "";//期初累计贷方发生次数
  
  private String begBalance = "";//期初余额
 
  private String thisPerDebit = "";//本期累计借方发生额
  private String thisPerLender = "";//本期累计贷方发生金额
  private String thisPerDebitDegree = "";//本期累计借方发生次数
  private String thisPerLenderDegree = "";//本期累计贷方发生次数
  
  private String endPerDebit = "";//期末累计解放发生额
  private String endPerLender = "";//期末累计贷方发生金额
  private String endPerDebitDegree = "";//期末累计借方发生次数
  private String endPerLenderDegree = "";//期末 累计贷方发生次数
  
  private String endBalance = "";//期初余额

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public String getBegBalance() {
    return begBalance;
  }

  public void setBegBalance(String begBalance) {
    this.begBalance = begBalance;
  }

  public String getBegPerDebit() {
    return begPerDebit;
  }

  public void setBegPerDebit(String begPerDebit) {
    this.begPerDebit = begPerDebit;
  }

  public String getBegPerDebitDegree() {
    return begPerDebitDegree;
  }

  public void setBegPerDebitDegree(String begPerDebitDegree) {
    this.begPerDebitDegree = begPerDebitDegree;
  }

  public String getBegPerLender() {
    return begPerLender;
  }

  public void setBegPerLender(String begPerLender) {
    this.begPerLender = begPerLender;
  }

  public String getBegPerLenderDegree() {
    return begPerLenderDegree;
  }

  public void setBegPerLenderDegree(String begPerLenderDegree) {
    this.begPerLenderDegree = begPerLenderDegree;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getEndBalance() {
    return endBalance;
  }

  public void setEndBalance(String endBalance) {
    this.endBalance = endBalance;
  }

  public String getEndPerDebit() {
    return endPerDebit;
  }

  public void setEndPerDebit(String endPerDebit) {
    this.endPerDebit = endPerDebit;
  }

  public String getEndPerDebitDegree() {
    return endPerDebitDegree;
  }

  public void setEndPerDebitDegree(String endPerDebitDegree) {
    this.endPerDebitDegree = endPerDebitDegree;
  }

  public String getEndPerLender() {
    return endPerLender;
  }

  public void setEndPerLender(String endPerLender) {
    this.endPerLender = endPerLender;
  }

  public String getEndPerLenderDegree() {
    return endPerLenderDegree;
  }

  public void setEndPerLenderDegree(String endPerLenderDegree) {
    this.endPerLenderDegree = endPerLenderDegree;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getHappenYear() {
    return happenYear;
  }

  public void setHappenYear(String happenYear) {
    this.happenYear = happenYear;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getThisPerDebit() {
    return thisPerDebit;
  }

  public void setThisPerDebit(String thisPerDebit) {
    this.thisPerDebit = thisPerDebit;
  }

  public String getThisPerDebitDegree() {
    return thisPerDebitDegree;
  }

  public void setThisPerDebitDegree(String thisPerDebitDegree) {
    this.thisPerDebitDegree = thisPerDebitDegree;
  }

  public String getThisPerLender() {
    return thisPerLender;
  }

  public void setThisPerLender(String thisPerLender) {
    this.thisPerLender = thisPerLender;
  }

  public String getThisPerLenderDegree() {
    return thisPerLenderDegree;
  }

  public void setThisPerLenderDegree(String thisPerLenderDegree) {
    this.thisPerLenderDegree = thisPerLenderDegree;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  
}
