package org.xpup.hafmis.syscollection.collloanback.dto;

import java.math.BigDecimal;

public class CollLoanbackTbListDTO {
  private String id = "";// 主键

  private String officeCode = "";// 办事处

  private String loanKouAcc = "";// 贷款账号

  private String kouPeople = "";// 扣款人

  private BigDecimal shouleMoney = new BigDecimal(0.00);// 应扣金额

  private BigDecimal realMoney = new BigDecimal(0.00);// 实扣金额

  private String batchNum = "";// 批次号

  private String status = "";// 状态

  private String loanBankId = "";// 银行

  private BigDecimal real_kou_money = new BigDecimal(0.00);

  private String real_count = "";

  private BigDecimal no_kou_money = new BigDecimal(0.00);

  private String no_count = "";

  private BigDecimal all_kou_money = new BigDecimal(0.00);

  private String all_count = "";

  private String bizdate = "";

  public String getAll_count() {
    return all_count;
  }

  public void setAll_count(String all_count) {
    this.all_count = all_count;
  }

  public BigDecimal getAll_kou_money() {
    return all_kou_money;
  }

  public void setAll_kou_money(BigDecimal all_kou_money) {
    this.all_kou_money = all_kou_money;
  }

  public String getNo_count() {
    return no_count;
  }

  public void setNo_count(String no_count) {
    this.no_count = no_count;
  }

  public BigDecimal getNo_kou_money() {
    return no_kou_money;
  }

  public void setNo_kou_money(BigDecimal no_kou_money) {
    this.no_kou_money = no_kou_money;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getKouPeople() {
    return kouPeople;
  }

  public void setKouPeople(String kouPeople) {
    this.kouPeople = kouPeople;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public BigDecimal getRealMoney() {
    return realMoney;
  }

  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public BigDecimal getShouleMoney() {
    return shouleMoney;
  }

  public void setShouleMoney(BigDecimal shouleMoney) {
    this.shouleMoney = shouleMoney;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getReal_count() {
    return real_count;
  }

  public void setReal_count(String real_count) {
    this.real_count = real_count;
  }

  public BigDecimal getReal_kou_money() {
    return real_kou_money;
  }

  public void setReal_kou_money(BigDecimal real_kou_money) {
    this.real_kou_money = real_kou_money;
  }

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }
}
