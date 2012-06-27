package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;

public interface IEmpAccountBS {
  //按要求查找数据
  public List findEmpAccountList_sy(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //按要求分段到月查找数据
  public List findEmpAccountMonthList_sy(Pagination pagination, SecurityInfo securityInfo);
  //按要求分段到日查找数据
  public List findEmpAccountDayList_sy(Pagination pagination, SecurityInfo securityInfo);
}
