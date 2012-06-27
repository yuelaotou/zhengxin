package org.xpup.hafmis.syscollection.paymng.paysure.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentHeadDTO implements Serializable {
  /**
   * 
   */
  private String payType = "";

  private String payStatus = "";
  
  private BigDecimal sumPayMoney = new BigDecimal(0);

  public BigDecimal getSumPayMoney() {
    return sumPayMoney;
  }

  public void setSumPayMoney(BigDecimal sumPayMoney) {
    this.sumPayMoney = sumPayMoney;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }
}
