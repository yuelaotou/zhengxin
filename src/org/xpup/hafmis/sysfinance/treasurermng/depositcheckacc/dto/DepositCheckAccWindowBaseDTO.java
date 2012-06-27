package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto;

import java.math.BigDecimal;

public class DepositCheckAccWindowBaseDTO {
  private String settDate="";
  private String settNum="";
  private BigDecimal money=new BigDecimal(0.00);
  private String type="";
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public String getSettNum() {
    return settNum;
  }
  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
}
