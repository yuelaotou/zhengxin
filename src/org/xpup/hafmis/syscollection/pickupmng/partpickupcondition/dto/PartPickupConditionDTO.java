package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto;

import java.math.BigDecimal;

public class PartPickupConditionDTO {
  private BigDecimal pickMoneyMax=new BigDecimal(0.00);
  private int pickTimeMax=0;
  private BigDecimal leavingsBalance=new BigDecimal(0.00);
  private int multiple=0;
  public BigDecimal getLeavingsBalance() {
    return leavingsBalance;
  }
  public void setLeavingsBalance(BigDecimal leavingsBalance) {
    this.leavingsBalance = leavingsBalance;
  }
  public BigDecimal getPickMoneyMax() {
    return pickMoneyMax;
  }
  public void setPickMoneyMax(BigDecimal pickMoneyMax) {
    this.pickMoneyMax = pickMoneyMax;
  }
  public int getPickTimeMax() {
    return pickTimeMax;
  }
  public void setPickTimeMax(int pickTimeMax) {
    this.pickTimeMax = pickTimeMax;
  }
  public int getMultiple() {
    return multiple;
  }
  public void setMultiple(int multiple) {
    this.multiple = multiple;
  }
}
