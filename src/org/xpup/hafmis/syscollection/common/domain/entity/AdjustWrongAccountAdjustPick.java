package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 错账调整头表-调提取L
 * 
 *@author 李娟
 *2007-6-25
 */
public class AdjustWrongAccountAdjustPick extends AdjustWrongAccountHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "调提取";
  }
  
}