package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 单位住房公积业务流水-调其他G
 * 
 *@author 李娟
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustOther extends OrgHAFAccountFlow{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "调其他";
  }
  
}