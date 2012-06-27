package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IAssureborrowerqueryBS {
 
  public List findAssureborrowerqueryList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  public List findAssureborrowerqueryPrintList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  
}
