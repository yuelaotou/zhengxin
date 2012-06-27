package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 错账调整-调缴存K
 * 
 *@author 李娟
 *2007-6-25
 */
public class AdjustWrongAccountAdjustPayment extends AdjustWrongAccountHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "调缴存";
  }
  
}