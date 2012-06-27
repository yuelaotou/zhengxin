package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;
/**
 * 和出纳扎账、银行存款日记账、账簿管理中的现金日记账、银行存款日记账公用
 * @author guojingping
 *
 */
public class CashDayClearTcShowListDTO {
  /**
   * 日期
   */
  private String credenceDate="";
  /**
   * 凭证号
   */
  private String credenceNum="";
  /**
   * 凭证字
   */
  private String credenceCharacter="";
  /**
   * 凭证字号
   */
  private String credenceChaNum="";
  private String temp_credenceChaNum="";
  /**
   * 摘要
   */
  private String summary="";
  private String temp_summary="";
  /**
   * 科目
   */
  private String subjectCode="";
  /**
   * 科目名称
   */
  private String subjectName="";
  /**
   * 借方金额
   */
  private BigDecimal debit=new BigDecimal(0.00);
  /**
   * 贷方金额
   */
  private BigDecimal credit=new BigDecimal(0.00);
  /**
   * 余额
   */
  private BigDecimal balance=new BigDecimal(0.00);
  /**
   * 结算号
   */
  private String settNum="";
  /**
   * 结算方式
   */
  private String settType="";
  private String temp_settType="";
  /**
   * 经手人
   */
  private String dopsn="";
  /**
   * 制单人
   */
  private String makebill="";
  /**
   * 状态
   */
  private String credenceSt="";
  /**
   * 结算日期
   */
  private String settDate="";
  /**
   * fn210表的主键
   */
  private String credenceId="";
  /**
   * fn210表会计凭证流水号
   */
  private String acredenceId="";
  /**
   * 办事处
   */
  private String office="";
  public String getCredenceDate() {
    return credenceDate;
  }
  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
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
  public String getCredenceSt() {
    return credenceSt;
  }
  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
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
  public String getMakebill() {
    return makebill;
  }
  public void setMakebill(String makebill) {
    this.makebill = makebill;
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
  public String getCredenceCharacter() {
    return credenceCharacter;
  }
  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }
  public String getCredenceChaNum() {
    return credenceChaNum;
  }
  public void setCredenceChaNum(String credenceChaNum) {
    this.credenceChaNum = credenceChaNum;
  }
  public String getTemp_settType() {
    return temp_settType;
  }
  public void setTemp_settType(String temp_settType) {
    this.temp_settType = temp_settType;
  }
  public String getTemp_summary() {
    return temp_summary;
  }
  public void setTemp_summary(String temp_summary) {
    this.temp_summary = temp_summary;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public String getTemp_credenceChaNum() {
    return temp_credenceChaNum;
  }
  public void setTemp_credenceChaNum(String temp_credenceChaNum) {
    this.temp_credenceChaNum = temp_credenceChaNum;
  }
  public String getAcredenceId() {
    return acredenceId;
  }
  public void setAcredenceId(String acredenceId) {
    this.acredenceId = acredenceId;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
