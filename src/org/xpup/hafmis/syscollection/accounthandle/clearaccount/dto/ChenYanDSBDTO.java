package org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChenYanDSBDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String bizdate = "";

  private BigDecimal monthpay = new BigDecimal(0.00);

  private BigDecimal pickup = new BigDecimal(0.00);

  private BigDecimal tranin = new BigDecimal(0.00);

  private BigDecimal tranout = new BigDecimal(0.00);

  private BigDecimal chgup = new BigDecimal(0.00);

  private BigDecimal chgdown = new BigDecimal(0.00);

  private BigDecimal pickloan = new BigDecimal(0.00);

  private BigDecimal balance = new BigDecimal(0.00);

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }

  public BigDecimal getChgdown() {
    return chgdown;
  }

  public void setChgdown(BigDecimal chgdown) {
    this.chgdown = chgdown;
  }

  public BigDecimal getChgup() {
    return chgup;
  }

  public void setChgup(BigDecimal chgup) {
    this.chgup = chgup;
  }

  public BigDecimal getMonthpay() {
    return monthpay;
  }

  public void setMonthpay(BigDecimal monthpay) {
    this.monthpay = monthpay;
  }

  public BigDecimal getPickloan() {
    return pickloan;
  }

  public void setPickloan(BigDecimal pickloan) {
    this.pickloan = pickloan;
  }

  public BigDecimal getPickup() {
    return pickup;
  }

  public void setPickup(BigDecimal pickup) {
    this.pickup = pickup;
  }

  public BigDecimal getTranin() {
    return tranin;
  }

  public void setTranin(BigDecimal tranin) {
    this.tranin = tranin;
  }

  public BigDecimal getTranout() {
    return tranout;
  }

  public void setTranout(BigDecimal tranout) {
    this.tranout = tranout;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
