package org.xpup.hafmis.sysloan.loanapply.issuenotice.dto;

import java.math.BigDecimal;

public class IssuenoticePrintDTO {
  private String lastPerson = "";

  private String vipchkPerson = "";

  private String finchkPerson = "";

  private String bankName = "";

  private String outAccount = "";

  private String inAccount = "";

  private BigDecimal money = new BigDecimal(0.00);

  private String username = "";

  private String bizDate = "";

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getFinchkPerson() {
    return finchkPerson;
  }

  public void setFinchkPerson(String finchkPerson) {
    this.finchkPerson = finchkPerson;
  }

  public String getInAccount() {
    return inAccount;
  }

  public void setInAccount(String inAccount) {
    this.inAccount = inAccount;
  }

  public String getLastPerson() {
    return lastPerson;
  }

  public void setLastPerson(String lastPerson) {
    this.lastPerson = lastPerson;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public String getOutAccount() {
    return outAccount;
  }

  public void setOutAccount(String outAccount) {
    this.outAccount = outAccount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getVipchkPerson() {
    return vipchkPerson;
  }

  public void setVipchkPerson(String vipchkPerson) {
    this.vipchkPerson = vipchkPerson;
  }

}
