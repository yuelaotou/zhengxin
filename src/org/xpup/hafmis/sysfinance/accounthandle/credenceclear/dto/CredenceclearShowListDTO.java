package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.dto;

import java.math.BigDecimal;

public class CredenceclearShowListDTO {
  /**
   * 日期
   */
  private String credenceDate = "";

  /**
   * 凭证字
   */
  private String credenceCharacter = "";

  /**
   * 凭证号
   */
  private String credenceNum = "";

  /**
   * 凭证字号
   */
  private String credenceChaNum = "";

  /**
   * 摘要
   */
  private String summary = "";

  private String temp_summary;

  /**
   * 科目
   */
  private String subjectCode = "";

  /**
   * 科目名称
   */
  private String subjectName = "";

  /**
   * 借方金额
   */
  private BigDecimal debit = new BigDecimal(0.00);

  /**
   * 贷方金额
   */
  private BigDecimal credit = new BigDecimal(0.00);

  /*
   * 制单人
   */
  private String makeBill = "";

  /**
   * 结算号
   */
  private String settNum = "";

  /**
   * 结算日期
   */
  private String settDate = "";

  /**
   * 凭证状态
   */
  private String credenceSt = "";

  /**
   * fn201表的主键
   */
  private String credenceId = "";

  /**
   * 用来转入弹出框的凭证字号
   */
  private String temp_credenceChaNum = "";
  
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

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }

  public String getTemp_summary() {
    return temp_summary;
  }

  public void setTemp_summary(String temp_summary) {
    this.temp_summary = temp_summary;
  }

  public String getCredenceCharacter() {
    return credenceCharacter;
  }

  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getMakeBill() {
    return makeBill;
  }

  public void setMakeBill(String makeBill) {
    this.makeBill = makeBill;
  }

  public String getTemp_credenceChaNum() {
    return temp_credenceChaNum;
  }

  public void setTemp_credenceChaNum(String temp_credenceChaNum) {
    this.temp_credenceChaNum = temp_credenceChaNum;
  }
}
