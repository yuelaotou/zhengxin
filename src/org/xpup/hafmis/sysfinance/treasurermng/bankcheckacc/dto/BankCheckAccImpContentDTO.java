package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class BankCheckAccImpContentDTO extends impDto {
  private static final long serialVersionUID = 1L;
  private String settdate="";
  private String subjectcode="";
  private String debit="";
  private String credit="";
  private String setttype="";
  private String settnum="";
  private String summary="";
  private String dopsn="";
  public String getCredit() {
    return credit;
  }
  public void setCredit(String credit) {
    this.credit = credit;
  }
  public String getDebit() {
    return debit;
  }
  public void setDebit(String debit) {
    this.debit = debit;
  }
  public String getSettdate() {
    return settdate;
  }
  public void setSettdate(String settdate) {
    this.settdate = settdate;
  }
  public String getSettnum() {
    return settnum;
  }
  public void setSettnum(String settnum) {
    this.settnum = settnum;
  }
  public String getSetttype() {
    return setttype;
  }
  public void setSetttype(String setttype) {
    this.setttype = setttype;
  }
  public String getSubjectcode() {
    return subjectcode;
  }
  public void setSubjectcode(String subjectcode) {
    this.subjectcode = subjectcode;
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
