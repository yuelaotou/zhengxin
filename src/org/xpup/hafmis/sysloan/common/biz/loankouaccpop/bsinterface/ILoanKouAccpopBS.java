package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ILoanKouAccpopBS {
  public List findLoanKouAccpopList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException;
}
