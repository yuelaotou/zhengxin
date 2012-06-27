package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICumulativeinfoFindBS {
  public List FindCumulativeinfo(SecurityInfo securityInfo,String officeCode,String collectionBankId,String queryTime)throws Exception,BusinessException;
  public List FindCollectionBankId(String officeCode)throws Exception,BusinessException;
  public List findFundbankmonthofyear(final SecurityInfo securityInfo,final String officeCode,final String collectionBankId, final String Year)throws Exception, BusinessException;
  public String find_bank_realname(final String user)throws Exception,BusinessException;
}
