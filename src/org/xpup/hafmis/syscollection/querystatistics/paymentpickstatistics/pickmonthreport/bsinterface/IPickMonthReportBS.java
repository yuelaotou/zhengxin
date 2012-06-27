package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPickMonthReportBS {
  
  public List queryPickMonRepInfo(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  public List queryPickMonRepBankPopInfo(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
}
