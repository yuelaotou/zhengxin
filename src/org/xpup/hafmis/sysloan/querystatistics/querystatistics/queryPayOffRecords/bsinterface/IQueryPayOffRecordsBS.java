package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form.QueryPayOffRecordsTaShowAF;
 

public interface IQueryPayOffRecordsBS {
 
  public QueryPayOffRecordsTaShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

}
