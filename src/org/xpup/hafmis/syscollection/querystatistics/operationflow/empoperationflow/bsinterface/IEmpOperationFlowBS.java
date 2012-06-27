package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IEmpOperationFlowBS {
  public List findEmpHAFAccountFlowList(Pagination pagination,SecurityInfo securityInfo);
  public List findEmpHAFAccountFlowTotalList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public List findEmpHAFAccountFlowPrintList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
}