package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto;

import java.math.BigDecimal;


public class OrgpaymentstatisticsDTO {
  private String id = "";
  
  private BigDecimal orgPay=new BigDecimal(0.00);
  
  private BigDecimal empPay = new BigDecimal(0.00);
  
  private Integer pay_month = new Integer(0);
  



  public Integer getPay_month() {
    return pay_month;
  }

  public void setPay_month(Integer pay_month) {
    this.pay_month = pay_month;
  }



  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    if(empPay != null && !empPay.equals("")){
      empPay=empPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.empPay = empPay;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    if(orgPay != null && !orgPay.equals("")){
      orgPay=orgPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    }
    this.orgPay = orgPay;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}