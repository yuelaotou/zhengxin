package org.xpup.hafmis.syscollection.collloanbackcheck.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICollLoanbackcheckBS {
  public List collLoanbackcheckForwardFind(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception;
  public List collLoanbackcheckForwardFindAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception;
  public void bizcheck(String id,SecurityInfo securityInfo) throws BusinessException, Exception;
  public String[] clearaccount(String id,SecurityInfo securityInfo) throws BusinessException, Exception;
  public void bizcheckAll(List list,SecurityInfo securityInfo) throws BusinessException, Exception;
  public void delbizcheck(String id,SecurityInfo securityInfo) throws BusinessException, Exception;
  public void delbizcheckAll(List list,SecurityInfo securityInfo) throws BusinessException, Exception;
}
