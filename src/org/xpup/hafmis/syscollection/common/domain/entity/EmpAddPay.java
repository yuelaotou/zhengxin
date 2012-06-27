package org.xpup.hafmis.syscollection.common.domain.entity;




/**
 * 缴存-个人补缴C
 * 
 *@author 李娟
 *2007-6-20
 */
public class EmpAddPay extends PaymentHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String payType;
  public String getPayType() {
    return "个人补缴";
  }
}