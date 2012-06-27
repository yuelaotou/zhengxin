package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 业务活动日志-个人补缴K
 * 
 *@author 李娟
 *2007-6-20
 */
public class EmpAddPayBizActivityLog extends BizActivityLog{
  
  private static final long serialVersionUID = -3745806964079944938L;
  
    /** persistent field */
    private String type;
  
    public String getType() {
      return "个人补缴";
    }
}