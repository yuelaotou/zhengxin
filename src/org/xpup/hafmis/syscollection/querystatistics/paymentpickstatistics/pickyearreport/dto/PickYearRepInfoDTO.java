package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.dto;

import java.math.BigDecimal;

public class PickYearRepInfoDTO {
  /*购房*/
  private Integer personCount_buyhouse;
  
  private BigDecimal pickMoney_buyhouse = new BigDecimal(0.00);
  
  private String pickMoneyRate_buyhouse = "";
  /*还贷*/
  private Integer personCount_callback;
  
  private BigDecimal pickMoney_callback = new BigDecimal(0.00);
  
  private String pickMoneyRate_callback = "";
  /*其他*/
  private Integer personCount_other;
  
  private BigDecimal pickMoney_other = new BigDecimal(0.00);
  
  private String pickMoneyRate_other = "";
  /*退休*/
  private Integer personCount_retire;
  
  private BigDecimal pickMoney_retire = new BigDecimal(0.00);
  
  private String pickMoneyRate_retire = "";
  /*失业*/
  private Integer personCount_jobless;
  
  private BigDecimal pickMoney_jobless = new BigDecimal(0.00);
  
  private String pickMoneyRate_jobless = "";
  /*死亡*/
  private Integer personCount_death;
  
  private BigDecimal pickMoney_death = new BigDecimal(0.00);
  
  private String pickMoneyRate_death = "";
  /*个贷扣款*/
  private Integer personCount_deduct;
  
  private BigDecimal pickMoney_deduct = new BigDecimal(0.00);
  
  private String pickMoneyRate_deduct = "";
  /*合计*/
  private Integer personCount_total;
  
  private BigDecimal pickMoney_total = new BigDecimal(0.00);
  
  private String month;
  
  private String pickReason;
  
  private Integer personCount_temp;
  
  private BigDecimal pickMoney_temp = new BigDecimal(0.00);


  public String getPickReason() {
    return pickReason;
  }

  public void setPickReason(String pickReason) {
    this.pickReason = pickReason;
  }

  public Integer getPersonCount_buyhouse() {
    return personCount_buyhouse;
  }

  public void setPersonCount_buyhouse(Integer personCount_buyhouse) {
    this.personCount_buyhouse = personCount_buyhouse;
  }

  public Integer getPersonCount_callback() {
    return personCount_callback;
  }

  public void setPersonCount_callback(Integer personCount_callback) {
    this.personCount_callback = personCount_callback;
  }

  public Integer getPersonCount_death() {
    return personCount_death;
  }

  public void setPersonCount_death(Integer personCount_death) {
    this.personCount_death = personCount_death;
  }

  public Integer getPersonCount_deduct() {
    return personCount_deduct;
  }

  public void setPersonCount_deduct(Integer personCount_deduct) {
    this.personCount_deduct = personCount_deduct;
  }

  public Integer getPersonCount_jobless() {
    return personCount_jobless;
  }

  public void setPersonCount_jobless(Integer personCount_jobless) {
    this.personCount_jobless = personCount_jobless;
  }

  public Integer getPersonCount_other() {
    return personCount_other;
  }

  public void setPersonCount_other(Integer personCount_other) {
    this.personCount_other = personCount_other;
  }

  public Integer getPersonCount_retire() {
    return personCount_retire;
  }

  public void setPersonCount_retire(Integer personCount_retire) {
    this.personCount_retire = personCount_retire;
  }

  public Integer getPersonCount_total() {
    return personCount_total;
  }

  public void setPersonCount_total(Integer personCount_total) {
    this.personCount_total = personCount_total;
  }

  public BigDecimal getPickMoney_buyhouse() {
    return pickMoney_buyhouse;
  }

  public void setPickMoney_buyhouse(BigDecimal pickMoney_buyhouse) {
    this.pickMoney_buyhouse = pickMoney_buyhouse;
  }

  public BigDecimal getPickMoney_callback() {
    return pickMoney_callback;
  }

  public void setPickMoney_callback(BigDecimal pickMoney_callback) {
    this.pickMoney_callback = pickMoney_callback;
  }

  public BigDecimal getPickMoney_death() {
    return pickMoney_death;
  }

  public void setPickMoney_death(BigDecimal pickMoney_death) {
    this.pickMoney_death = pickMoney_death;
  }

  public BigDecimal getPickMoney_deduct() {
    return pickMoney_deduct;
  }

  public void setPickMoney_deduct(BigDecimal pickMoney_deduct) {
    this.pickMoney_deduct = pickMoney_deduct;
  }

  public BigDecimal getPickMoney_jobless() {
    return pickMoney_jobless;
  }

  public void setPickMoney_jobless(BigDecimal pickMoney_jobless) {
    this.pickMoney_jobless = pickMoney_jobless;
  }

  public BigDecimal getPickMoney_other() {
    return pickMoney_other;
  }

  public void setPickMoney_other(BigDecimal pickMoney_other) {
    this.pickMoney_other = pickMoney_other;
  }

  public BigDecimal getPickMoney_retire() {
    return pickMoney_retire;
  }

  public void setPickMoney_retire(BigDecimal pickMoney_retire) {
    this.pickMoney_retire = pickMoney_retire;
  }

  public BigDecimal getPickMoney_total() {
    return pickMoney_total;
  }

  public void setPickMoney_total(BigDecimal pickMoney_total) {
    this.pickMoney_total = pickMoney_total;
  }

  public Integer getPersonCount_temp() {
    return personCount_temp;
  }

  public void setPersonCount_temp(Integer personCount_temp) {
    this.personCount_temp = personCount_temp;
  }

  public BigDecimal getPickMoney_temp() {
    return pickMoney_temp;
  }

  public void setPickMoney_temp(BigDecimal pickMoney_temp) {
    this.pickMoney_temp = pickMoney_temp;
  }

  public String getPickMoneyRate_buyhouse() {
    return pickMoneyRate_buyhouse;
  }

  public void setPickMoneyRate_buyhouse(String pickMoneyRate_buyhouse) {
    this.pickMoneyRate_buyhouse = pickMoneyRate_buyhouse;
  }

  public String getPickMoneyRate_callback() {
    return pickMoneyRate_callback;
  }

  public void setPickMoneyRate_callback(String pickMoneyRate_callback) {
    this.pickMoneyRate_callback = pickMoneyRate_callback;
  }

  public String getPickMoneyRate_death() {
    return pickMoneyRate_death;
  }

  public void setPickMoneyRate_death(String pickMoneyRate_death) {
    this.pickMoneyRate_death = pickMoneyRate_death;
  }

  public String getPickMoneyRate_deduct() {
    return pickMoneyRate_deduct;
  }

  public void setPickMoneyRate_deduct(String pickMoneyRate_deduct) {
    this.pickMoneyRate_deduct = pickMoneyRate_deduct;
  }

  public String getPickMoneyRate_jobless() {
    return pickMoneyRate_jobless;
  }

  public void setPickMoneyRate_jobless(String pickMoneyRate_jobless) {
    this.pickMoneyRate_jobless = pickMoneyRate_jobless;
  }

  public String getPickMoneyRate_other() {
    return pickMoneyRate_other;
  }

  public void setPickMoneyRate_other(String pickMoneyRate_other) {
    this.pickMoneyRate_other = pickMoneyRate_other;
  }

  public String getPickMoneyRate_retire() {
    return pickMoneyRate_retire;
  }

  public void setPickMoneyRate_retire(String pickMoneyRate_retire) {
    this.pickMoneyRate_retire = pickMoneyRate_retire;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }
  
  
}
