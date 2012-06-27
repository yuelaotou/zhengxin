package org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto;

import java.math.BigDecimal;

public class CollByFundBankDTO {

  private String collBankId;

  private BigDecimal money = new BigDecimal(0.00);

  private int count = 0;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }
}
