package org.xpup.hafmis.syscollection.accounthandle.clearinterest.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IClearaccountBS {
  //根据条件查询可以办理结息的单位列表  
  public List findClearaccountListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //根据条件查询全部可以办理结息的单位列表
  public List findClearaccountAllListByCriterions(Pagination pagination,SecurityInfo securityInfo)  throws Exception, BusinessException ;
  //扎账
  public String clearacount(String[] rowArray,String officecode,String bizDate,String ip,String oper, SecurityInfo securityInfo,String flag)throws BusinessException ,Exception;
}
