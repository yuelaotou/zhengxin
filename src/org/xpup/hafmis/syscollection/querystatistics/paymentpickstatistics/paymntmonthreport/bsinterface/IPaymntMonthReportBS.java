package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPaymntMonthReportBS {
  public List queryPaymntMonthRepInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
