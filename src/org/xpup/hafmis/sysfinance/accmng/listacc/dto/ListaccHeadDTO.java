package org.xpup.hafmis.sysfinance.accmng.listacc.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ListaccHeadDTO implements ExpDto {
  private String credenceDate;

  private String credenceNum;

  private String summary;

  private String debit;

  private String credit;

  private String balance;

  public String getInfo(String info) {
    if (info.equals("credenceNum"))
      return this.credenceNum;
    if (info.equals("credenceDate"))
      return this.credenceDate;
    if (info.equals("summary"))
      return this.summary;
    if (info.equals("debit"))
      return this.debit;
    if (info.equals("credit"))
      return this.credit;
    if (info.equals("balance"))
      return this.balance;
    return null;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getCredenceDate() {
    return credenceDate;
  }

  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }

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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

}
