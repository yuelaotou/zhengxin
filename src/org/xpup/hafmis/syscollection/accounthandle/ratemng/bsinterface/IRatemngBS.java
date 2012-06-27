package org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;

public interface IRatemngBS {
  public List findRatemngList_sy(Pagination pagination) throws Exception, BusinessException;
  public void removeRatemng_sy(String id,SecurityInfo securityInfo);
  public void useRatemng_sy(SecurityInfo securityInfo) throws BusinessException;
  public String checkOfficeCode(String officecode);
  public void addRatemng_sy(HafInterestRate hafInterestRate, String bizDate, SecurityInfo securityInfo)throws BusinessException;
}
