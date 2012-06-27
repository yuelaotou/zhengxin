package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICredenceclearBS {
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp);
  public Object[] findCashDayClearTbList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception;
  public void credenceclear(String id[],SecurityInfo securityInfo)throws Exception, BusinessException; 
  public void credenceclearAll(List countList,SecurityInfo securityInfo)throws Exception, BusinessException; 
  public void credenceclearSaveOperLog(SecurityInfo securityInfo)throws Exception;
  public void credenceclearSaveBizLog(String credenceNum,String credenceDate,String office,SecurityInfo securityInfo)throws Exception;
}
