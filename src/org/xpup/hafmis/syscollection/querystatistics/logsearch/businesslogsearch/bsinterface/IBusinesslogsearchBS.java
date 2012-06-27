package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;


public interface IBusinesslogsearchBS {

  public List findLogFlowList(Pagination pagination, SecurityInfo securityInfo)throws Exception;
  
  public List findAllUser(Pagination pagination, SecurityInfo securityInfo) throws Exception;
  
  public BizcheckDetailAF findOrgHAFAccountFlowByID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public List findAllLogFlowList(Pagination pagination, SecurityInfo securityInfo) throws Exception;
}
