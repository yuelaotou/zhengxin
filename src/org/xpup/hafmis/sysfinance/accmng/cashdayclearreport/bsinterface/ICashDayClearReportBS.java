package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.bsinterface;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICashDayClearReportBS {
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo);
  public Object[] findCashDayClearReportList(String credenceType,Pagination pagination,SecurityInfo securityInfo) throws Exception;
}
