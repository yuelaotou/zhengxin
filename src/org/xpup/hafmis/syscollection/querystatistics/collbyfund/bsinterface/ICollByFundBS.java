package org.xpup.hafmis.syscollection.querystatistics.collbyfund.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.form.CollByFundAF;

public interface ICollByFundBS {

  public CollByFundAF queryCollByFundByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
