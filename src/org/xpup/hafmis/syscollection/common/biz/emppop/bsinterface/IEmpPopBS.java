package org.xpup.hafmis.syscollection.common.biz.emppop.bsinterface;

import java.util.List;


import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

public interface IEmpPopBS {
  
  public List findEmployee(Pagination pagination) throws Exception, BusinessException;
}
