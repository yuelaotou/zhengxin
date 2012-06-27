package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto;

import java.math.BigDecimal;

public class SearchLackInfoHeadDTO {

  private BigDecimal orgpayLack = new BigDecimal(0.00);//Ç·µ¥Î»½É¶î
  private BigDecimal emppayLack = new BigDecimal(0.00);//Ç·Ö°¹¤½É¶î
  private BigDecimal sumpayLack = new BigDecimal(0.00);//Ç·½É½ð¶î
  
  public BigDecimal getEmppayLack() {
    return emppayLack;
  }
  public void setEmppayLack(BigDecimal emppayLack) {
    this.emppayLack = emppayLack;
  }
  public BigDecimal getOrgpayLack() {
    return orgpayLack;
  }
  public void setOrgpayLack(BigDecimal orgpayLack) {
    this.orgpayLack = orgpayLack;
  }
  public BigDecimal getSumpayLack() {
    return sumpayLack;
  }
  public void setSumpayLack(BigDecimal sumpayLack) {
    this.sumpayLack = sumpayLack;
  }
}
