package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.bsinterface;

import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IQueryOperationgLogBS {
  public Map queryListByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException ;
    
  public List queryCellListByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException ;
  
}
