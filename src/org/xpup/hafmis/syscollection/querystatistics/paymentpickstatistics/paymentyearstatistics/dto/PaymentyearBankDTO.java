package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto;

import java.math.BigDecimal;

public class PaymentyearBankDTO {

  private int hushu = 0;//户数
  private int renshu =0;//人数
  private BigDecimal jiner = new BigDecimal(0.00);//金额
  
  public int getHushu() {
    return hushu;
  }
  public void setHushu(int hushu) {
    this.hushu = hushu;
  }
  public BigDecimal getJiner() {
    return jiner;
  }
  public void setJiner(BigDecimal jiner) {
    this.jiner = jiner;
  }
  public int getRenshu() {
    return renshu;
  }
  public void setRenshu(int renshu) {
    this.renshu = renshu;
  }
  
  
}
