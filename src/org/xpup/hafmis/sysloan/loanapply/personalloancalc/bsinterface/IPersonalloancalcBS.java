package org.xpup.hafmis.sysloan.loanapply.personalloancalc.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPersonalloancalcBS {
  public List queryCorpusInterestList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;
}
