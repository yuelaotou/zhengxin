package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * 返回收入列表DTO
 * CredenceFillinIncomeDTO
 * @Version : v1.0
 * @author : 杨光
 * @Date : 2008.12.16
 */
public class CredenceFillinIncomeExpenseDTO {
  private String orgId = "";

  private String orgName = "";

  private String moneyBank = "";
  
  private String debit = "";

  private String credit = "";

  private String interest = "";

  private String sett_date = "";

  private String note_num = "";

  private String biz_type = "";

  public String getBiz_type() {
    return biz_type;
  }

  public void setBiz_type(String biz_type) {
    this.biz_type = biz_type;
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

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

  public String getMoneyBank() {
    return moneyBank;
  }

  public void setMoneyBank(String moneyBank) {
    this.moneyBank = moneyBank;
  }

  public String getNote_num() {
    return note_num;
  }

  public void setNote_num(String note_num) {
    this.note_num = note_num;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getSett_date() {
    return sett_date;
  }

  public void setSett_date(String sett_date) {
    this.sett_date = sett_date;
  }
}
