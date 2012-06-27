package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form.OverdueQueryTaAF;

public interface IOverdueQueryBS {
  public void updatePunishInterest(SecurityInfo securityInfo)
      throws BusinessException,Exception;

  public OverdueQueryTaAF queryOverdueList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  public OverdueQueryTaAF queryOverdueAllList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  public List queryOverdueInfByContractid(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
  
  public OverdueQueryTaAF queryExpList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
}
