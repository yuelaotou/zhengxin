package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 错账调整-调其他G
 * 
 *@author 李娟
 *2007-6-25
 */
public class AdjustWrongAccountAdjustOther extends AdjustWrongAccountHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "调其他";
  }
  
}