package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.bsinterface;



import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICredencecheckBS {
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp);
  public Object[] findCashDayClearTbList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception;
  public void credencecheckCheck(String id,SecurityInfo securityInfo)throws Exception, BusinessException;
  public void credencecheckCheck_yg(String id,SecurityInfo securityInfo,String a)throws Exception, BusinessException;
  public void credencecheckDelCheck(String id,SecurityInfo securityInfo)throws Exception, BusinessException;  
  public void credencecheckCheckAll(List list,SecurityInfo securityInfo)throws Exception, BusinessException;
  public void credencecheckDelCheckAll(List list,SecurityInfo securityInfo)throws Exception, BusinessException;
}
