package org.xpup.hafmis.sysloan.loanapply.loandelete.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ILoandeleteBS {

  public List findLoanerlogoutTbList(Pagination pagination,List loanbankList) throws Exception;
  public void deleteById(String id,SecurityInfo securityInfo)throws BusinessException,Exception;

}
