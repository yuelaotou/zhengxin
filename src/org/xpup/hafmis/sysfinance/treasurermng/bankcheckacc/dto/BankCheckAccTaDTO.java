package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import java.math.BigDecimal;

public class BankCheckAccTaDTO {
  private String office="";//所属办事处
  private String temp_office="";
  private String subjectCode="";//科目
  private String summary="";//摘要
  private BigDecimal debit=new BigDecimal(0.00);//银行借方金额
  private BigDecimal credit=new BigDecimal(0.00);//银行贷方金额
  private String settType="";//结算方式
  private String settNum="";//结算号
  private String dopsn="";//经手人
  private String settDate="";//结算日期
  private String type="";//用来标识是否从维护页面进来的
  private String credenceId="";//主键id，为修改准备的
  public String getCredenceId() {
    return credenceId;
  }
  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getTemp_office() {
    return temp_office;
  }
  public void setTemp_office(String temp_office) {
    this.temp_office = temp_office;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public BigDecimal getCredit() {
    return credit;
  }
  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }
  public BigDecimal getDebit() {
    return debit;
  }
  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }
  public String getDopsn() {
    return dopsn;
  }
  public void setDopsn(String dopsn) {
    this.dopsn = dopsn;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public String getSettNum() {
    return settNum;
  }
  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }
  public String getSettType() {
    return settType;
  }
  public void setSettType(String settType) {
    this.settType = settType;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
}
