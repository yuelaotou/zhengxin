package org.xpup.hafmis.sysloan.common.biz.emppop.bsinterface;

import java.util.List;


import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;




public interface IEmpPopBS {
  
  public List findEmployee(Pagination pagination) throws Exception, BusinessException;
}
