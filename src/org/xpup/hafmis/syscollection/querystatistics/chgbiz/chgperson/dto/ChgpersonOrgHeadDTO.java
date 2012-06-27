package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto;

import java.math.BigDecimal;

public class ChgpersonOrgHeadDTO {

  private Integer orgCount = new Integer(0);//调整单位数
  private BigDecimal preSumPay = new BigDecimal(0.00);//调整前应缴总额
  private BigDecimal sumPay = new BigDecimal(0.00);//调整后应缴总额
  
  public Integer getOrgCount() {
    return orgCount;
  }
  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
  }
  public BigDecimal getPreSumPay() {
    return preSumPay;
  }
  public void setPreSumPay(BigDecimal preSumPay) {
    this.preSumPay = preSumPay;
  }
  public BigDecimal getSumPay() {
    return sumPay;
  }
  public void setSumPay(BigDecimal sumPay) {
    this.sumPay = sumPay;
  }
}
