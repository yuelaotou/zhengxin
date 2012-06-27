package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoTotalDTO;

public interface IOrgAccountInfoBS {
  public List findOrgAccountInfo(Pagination pagination,SecurityInfo securityInfo);
  public List findOrgAccountInfoByMonth(Pagination pagination,SecurityInfo securityInfo);
  public List findOrgAccountInfoByDay(Pagination pagination,SecurityInfo securityInfo);
  public OrgAccountInfoTotalDTO findOrgAccountInfoTotal(Pagination pagination,SecurityInfo securityInfo);
  public OrgAccountInfoTotalDTO findOrgAccountInfoMonthTotal(Pagination pagination,SecurityInfo securityInfo);
  public OrgAccountInfoTotalDTO findOrgAccountInfoDayTotal(Pagination pagination,SecurityInfo securityInfo);
  public List findOrgAccountInfoPrint(Pagination pagination,SecurityInfo securityInfo);
  public List findOrgAccountInfoByMonthPrint(Pagination pagination,SecurityInfo securityInfo);
  public List findOrgAccountInfoByDayPrint(Pagination pagination,SecurityInfo securityInfo);
}
