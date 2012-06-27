package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import java.math.BigDecimal;

public class BankCheckAccTbFindDTO {
  private String subjectCode="";//科目
  private String subjectName="";//科目名称
  private String settDateSt="";//结算日期开始
  private String settDateEnd="";//结算日期结束
  private String settNumSt="";//结算号开始
  private String settNumEnd="";//结算号结束
  private String summary="";//摘要
  private String moneySt="";//金额开始
  private String moneyEnd="";//金额结束
  private BigDecimal debitSum=new BigDecimal(0.00);//银行借方金额总计
  private BigDecimal creditSum=new BigDecimal(0.00);//银行贷方金额总计
  public BigDecimal getCreditSum() {
    return creditSum;
  }
  public void setCreditSum(BigDecimal creditSum) {
    this.creditSum = creditSum;
  }
  public BigDecimal getDebitSum() {
    return debitSum;
  }
  public void setDebitSum(BigDecimal debitSum) {
    this.debitSum = debitSum;
  }
  public String getSettDateEnd() {
    return settDateEnd;
  }
  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }
  public String getSettDateSt() {
    return settDateSt;
  }
  public void setSettDateSt(String settDateSt) {
    this.settDateSt = settDateSt;
  }
  public String getSettNumEnd() {
    return settNumEnd;
  }
  public void setSettNumEnd(String settNumEnd) {
    this.settNumEnd = settNumEnd;
  }
  public String getSettNumSt() {
    return settNumSt;
  }
  public void setSettNumSt(String settNumSt) {
    this.settNumSt = settNumSt;
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
  public String getMoneyEnd() {
    return moneyEnd;
  }
  public void setMoneyEnd(String moneyEnd) {
    this.moneyEnd = moneyEnd;
  }
  public String getMoneySt() {
    return moneySt;
  }
  public void setMoneySt(String moneySt) {
    this.moneySt = moneySt;
  }
}
