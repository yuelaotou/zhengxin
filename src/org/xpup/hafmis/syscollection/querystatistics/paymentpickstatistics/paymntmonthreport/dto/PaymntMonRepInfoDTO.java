package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.dto;

import java.math.BigDecimal;

public class PaymntMonRepInfoDTO {

  private String collBank;

  private String collBankId;
  
  private String bizType;
  
  private BigDecimal money;

  private BigDecimal prevMonBalance;

  private BigDecimal curMonthPay;

  private BigDecimal curAddPay;

  private BigDecimal curOverPay;
  
  private BigDecimal overPayBalance;

  private BigDecimal curSumPay;

  private BigDecimal curYearSumPay;

  private Integer curMonthOrgCount;

  private Integer curMonthPsnCount;

  public String getCollBank() {
    return collBank;
  }

  public void setCollBank(String collBank) {
    this.collBank = collBank;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public BigDecimal getCurAddPay() {
    return curAddPay;
  }

  public void setCurAddPay(BigDecimal curAddPay) {
    this.curAddPay = curAddPay;
  }

  public Integer getCurMonthOrgCount() {
    return curMonthOrgCount;
  }

  public void setCurMonthOrgCount(Integer curMonthOrgCount) {
    this.curMonthOrgCount = curMonthOrgCount;
  }

  public BigDecimal getCurMonthPay() {
    return curMonthPay;
  }

  public void setCurMonthPay(BigDecimal curMonthPay) {
    this.curMonthPay = curMonthPay;
  }

  public Integer getCurMonthPsnCount() {
    return curMonthPsnCount;
  }

  public void setCurMonthPsnCount(Integer curMonthPsnCount) {
    this.curMonthPsnCount = curMonthPsnCount;
  }

  public BigDecimal getCurOverPay() {
    return curOverPay;
  }

  public void setCurOverPay(BigDecimal curOverPay) {
    this.curOverPay = curOverPay;
  }

  public BigDecimal getCurSumPay() {
    return curSumPay;
  }

  public void setCurSumPay(BigDecimal curSumPay) {
    this.curSumPay = curSumPay;
  }

  public BigDecimal getCurYearSumPay() {
    return curYearSumPay;
  }

  public void setCurYearSumPay(BigDecimal curYearSumPay) {
    this.curYearSumPay = curYearSumPay;
  }

  public BigDecimal getPrevMonBalance() {
    return prevMonBalance;
  }

  public void setPrevMonBalance(BigDecimal prevMonBalance) {
    this.prevMonBalance = prevMonBalance;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getOverPayBalance() {
    return overPayBalance;
  }

  public void setOverPayBalance(BigDecimal overPayBalance) {
    this.overPayBalance = overPayBalance;
  }

}
