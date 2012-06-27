package org.xpup.hafmis.syscommon.checkclearaccount.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
/**
 * 
 * @author 王菱
 */
public interface ICheckclearaccountBS {
  //验证当前系统当前日期是否扎账
  public String checkclearaccount(SecurityInfo securityInfo) throws Exception, BusinessException;
}