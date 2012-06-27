package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class OrgAccountInfoDTO extends DomainObject{
  
  private String officecode="";
  private String officename="";
  private String collbankid="";
  private String collbankname="";
  private Serializable orgid="";
  private String orgname="";
  /** 期初余额 */
  private BigDecimal prebalance=new BigDecimal(0.00); 
  /** 本期贷方发生额 */
  private BigDecimal temp_credit=new BigDecimal(0.00);
  /** 本期借方发生额 */
  private BigDecimal temp_debit=new BigDecimal(0.00);
  /** 本期贷方笔数 */
  private String countCredit="0";
  /** 本期借方笔数 */
  private String countDebit="0"; 
  /** 挂账金额 */
  private BigDecimal orgOverMoney = new BigDecimal(0.00);
  /** 期末余额 */
  private BigDecimal curbalance=new BigDecimal(0.00);
  /** 挂账余额 */
  private BigDecimal orgOverPaybalance=new BigDecimal(0.00);
  /** 账面余额 */
  private BigDecimal balance=new BigDecimal(0.00);
  
  private String opTime="";
  
  
  private BigDecimal accountBalance=new BigDecimal(0.00);  // 帐户余额

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    if(balance != null && !balance.equals("")){
      balance = balance.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.balance = balance;
  }

  public String getCollbankid() {
    return collbankid;
  }

  public void setCollbankid(String collbankid) {
    this.collbankid = collbankid;
  }

  public String getCollbankname() {
    return collbankname;
  }

  public void setCollbankname(String collbankname) {
    this.collbankname = collbankname;
  }

  public String getCountCredit() {
    return countCredit;
  }

  public void setCountCredit(String countCredit) {
    this.countCredit = countCredit;
  }

  public String getCountDebit() {
    return countDebit;
  }

  public void setCountDebit(String countDebit) {
    this.countDebit = countDebit;
  }

  public BigDecimal getCurbalance() {
    return curbalance;
  }

  public void setCurbalance(BigDecimal curbalance) {
    if(curbalance != null && !curbalance.equals("")){
      curbalance = curbalance.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.curbalance = curbalance;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getOfficename() {
    return officename;
  }

  public void setOfficename(String officename) {
    this.officename = officename;
  }

  public String getOpTime() {
    return opTime;
  }

  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }

  public Serializable getOrgid() {
    return orgid;
  }

  public void setOrgid(Serializable orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }


  public BigDecimal getOrgOverMoney() {
    return orgOverMoney;
  }

  public void setOrgOverMoney(BigDecimal orgOverMoney) {
    if(orgOverMoney != null && !orgOverMoney.equals("")){
      orgOverMoney = orgOverMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.orgOverMoney = orgOverMoney;
  }

  public BigDecimal getOrgOverPaybalance() {
    return orgOverPaybalance;
  }

  public void setOrgOverPaybalance(BigDecimal orgOverPaybalance) {
    if(orgOverPaybalance != null && !orgOverPaybalance.equals("")){
      orgOverPaybalance = orgOverPaybalance.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.orgOverPaybalance = orgOverPaybalance;
  }

  public BigDecimal getPrebalance() {
    return prebalance;
  }

  public void setPrebalance(BigDecimal prebalance) {
    if(prebalance != null && !prebalance.equals("")){
      prebalance = prebalance.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.prebalance = prebalance;
  }

  public BigDecimal getTemp_credit() {
    return temp_credit;
  }

  public void setTemp_credit(BigDecimal temp_credit) {
    if(temp_credit != null && !temp_credit.equals("")){
      temp_credit = temp_credit.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.temp_credit = temp_credit;
  }

  public BigDecimal getTemp_debit() {
    return temp_debit;
  }

  public void setTemp_debit(BigDecimal temp_debit) {
    if(temp_debit != null && !temp_debit.equals("")){
      temp_debit = temp_debit.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.temp_debit = temp_debit;
  }

  public BigDecimal getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(BigDecimal accountBalance) {
    this.accountBalance = accountBalance;
  }
}