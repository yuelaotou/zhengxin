package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.form.InterestTaAF;

public interface IInterestBS {
  public InterestTaAF queryYearAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  public InterestTaAF queryMonthAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
  
  public InterestTaAF queryDayAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
}
