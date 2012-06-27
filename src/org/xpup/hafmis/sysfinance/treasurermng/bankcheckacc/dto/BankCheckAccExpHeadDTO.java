/*结算日期、科目、借方金额、贷方金额、结算方式、结算号、摘要、经手人*/
package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class BankCheckAccExpHeadDTO implements ExpDto {
  private static final long serialVersionUID = 0L;
  private String settDate;
  private String subjectCode;
  private String debit;
  private String credit;
  private String settType;
  private String settNum;
  private String summary;
  private String dopsn;
  public String getDebit() {
    return debit;
  }
  public void setDebit(String debit) {
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
  public String getCredit() {
    return credit;
  }
  public void setCredit(String credit) {
    this.credit = credit;
  }
  public String getInfo(String s) {
    if (s.equals("settDate"))
      return settDate;
    if (s.equals("subjectCode"))
      return subjectCode;
    if (s.equals("credit"))
      return credit;
    if (s.equals("debit"))
      return debit;
    if (s.equals("settType"))
      return settType;
    if (s.equals("settNum"))
      return settNum;
    if (s.equals("summary"))
      return summary;
    if (s.equals("dopsn"))
      return dopsn;

    else
      return null;
  }
  public String getDopsn() {
    return dopsn;
  }
  public void setDopsn(String dopsn) {
    this.dopsn = dopsn;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
}
