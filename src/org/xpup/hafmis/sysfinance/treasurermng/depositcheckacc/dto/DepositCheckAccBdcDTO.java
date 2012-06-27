package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto;

import java.math.BigDecimal;

public class DepositCheckAccBdcDTO {
/**
 * 银行日记账列表
 */
  private String settDate="";//结算日期
  private String subjectCode="";//科目代码
  private String settType="";//结算方式
  private String temp_settType="";
  private String settNum="";//结算号
  private String credenceCharacter="";//凭证字
  private String credenceNum="";//凭证号
  private String credenceChaNum="";//凭证字号
  private BigDecimal debit=new BigDecimal(0.00);//借方金额
  private BigDecimal credit=new BigDecimal(0.00);//贷方金额
  private String credenceDate="";//凭证日期
  private String credenceId="";//fn210主键
  private String type="";//用来标志页面变颜色的
  public String getCredenceChaNum() {
    return credenceChaNum;
  }
  public void setCredenceChaNum(String credenceChaNum) {
    this.credenceChaNum = credenceChaNum;
  }
  public String getCredenceDate() {
    return credenceDate;
  }
  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
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
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getCredenceId() {
    return credenceId;
  }
  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getCredenceCharacter() {
    return credenceCharacter;
  }
  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }
  public String getTemp_settType() {
    return temp_settType;
  }
  public void setTemp_settType(String temp_settType) {
    this.temp_settType = temp_settType;
  }
  public String getCredenceNum() {
    return credenceNum;
  }
  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }
}
