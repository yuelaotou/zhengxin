package org.xpup.hafmis.syscollection.common.domain.entity;




/**
 * 缴存-单位挂账D
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgExcessPayment extends PaymentHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String payType;

  public String getPayType() {
    return "单位挂账";
  }
}