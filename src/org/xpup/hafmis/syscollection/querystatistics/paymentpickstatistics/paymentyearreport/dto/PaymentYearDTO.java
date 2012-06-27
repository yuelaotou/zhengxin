package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.dto;

import java.math.BigDecimal;

public class PaymentYearDTO {

  private String officecode;

  private String collBankId;

  private String yearmonth;

  private int count = 0;

  private int person = 0;

  private BigDecimal money = new BigDecimal(0.00);

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public int getPerson() {
    return person;
  }

  public void setPerson(int person) {
    this.person = person;
  }

  public String getYearmonth() {
    return yearmonth;
  }

  public void setYearmonth(String yearmonth) {
    this.yearmonth = yearmonth;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

}
