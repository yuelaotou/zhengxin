package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;

/**
 * 和银行存款日记账公用
 * @author guojingping
 *
 */

public class CashDayClearTaDTO {
  /**
   * 所属办事处
   */
  private String office="";
  /**
   * 科目
   */
  private String subjectCode="";
  /**
   * 凭证日期
   */
  private String credenceDate="";
  /**
   * 凭证字
   */
  private String credenceCharacter="";
  /**
   * 摘要
   */
  private String summray="";
  /**
   * 借方金额
   */
  private BigDecimal debit=new BigDecimal(0.00);
  /**
   * 贷方金额
   */
  private BigDecimal credit=new BigDecimal(0.00);
  /**
   * 结算方式
   */
  private String settType="";
  /**
   * 结算号
   */
  private String settNum="";
  /**
   * 经手人
   */
  private String dopsn="";
  /**
   * 结算日期
   */
  private String settDate="";
  /**
   * 用来判断是修改页面还是添加页面
   */
  private String type="";
  /**
   * fn210主键
   */
  private String credenceId="";
  /**
   * 凭证号
   */
  private String credenceNum="";
  /**
   * 会计凭证流水号
   */
  private String acredenceId="";
  public String getCredenceNum() {
    return credenceNum;
  }
  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
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
  public String getSummray() {
    return summray;
  }
  public void setSummray(String summray) {
    this.summray = summray;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAcredenceId() {
    return acredenceId;
  }
  public void setAcredenceId(String acredenceId) {
    this.acredenceId = acredenceId;
  }
}
