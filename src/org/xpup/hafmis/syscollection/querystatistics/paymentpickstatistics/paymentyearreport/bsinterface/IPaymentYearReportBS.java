package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPaymentYearReportBS {
  public List queryPaymentYearReportInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
