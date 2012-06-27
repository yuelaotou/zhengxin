package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;

public interface IPrincipalBS {
  public PrincipalTaAF queryYearAccList(Pagination pagination, SecurityInfo securityInfo)
    throws BusinessException,Exception;
  
  public PrincipalTaAF queryMonthAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
  
  public PrincipalTaAF queryDayAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
}
