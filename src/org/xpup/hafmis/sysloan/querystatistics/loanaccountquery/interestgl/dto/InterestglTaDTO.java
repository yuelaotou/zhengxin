package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto;

public class InterestglTaDTO {
  
  private String id = "";
  private String office = "";//办事处
  private String loanBank = "";//放款银行
  private String developerName = "";//开发商
  private String floorNum = "";//楼盘
  private String assistantOrgName = "";//担保公司
  private String happenYear = "";//发生年份
  private String begInterest = "";//期初利息
  private String begOverdueInterest = "";//期初逾期利息
  private String thisInterest = "";//本期利息
  private String thisOverdueInterest = "";//本期逾期利息
  private String endInterest = "";//期末利息
  private String endOverdueInterest = "";//期末逾期利息
  
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }
  public String getBegInterest() {
    return begInterest;
  }
  public void setBegInterest(String begInterest) {
    this.begInterest = begInterest;
  }
  public String getBegOverdueInterest() {
    return begOverdueInterest;
  }
  public void setBegOverdueInterest(String begOverdueInterest) {
    this.begOverdueInterest = begOverdueInterest;
  }
  public String getDeveloperName() {
    return developerName;
  }
  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }
  public String getEndInterest() {
    return endInterest;
  }
  public void setEndInterest(String endInterest) {
    this.endInterest = endInterest;
  }
  public String getEndOverdueInterest() {
    return endOverdueInterest;
  }
  public void setEndOverdueInterest(String endOverdueInterest) {
    this.endOverdueInterest = endOverdueInterest;
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
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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
  public String getThisInterest() {
    return thisInterest;
  }
  public void setThisInterest(String thisInterest) {
    this.thisInterest = thisInterest;
  }
  public String getThisOverdueInterest() {
    return thisOverdueInterest;
  }
  public void setThisOverdueInterest(String thisOverdueInterest) {
    this.thisOverdueInterest = thisOverdueInterest;
  }
  
  
}
