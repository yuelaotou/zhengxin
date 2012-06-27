package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.bsinterface;


import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
public interface IQueryorgversionintBS {
  public List queryList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;

}
