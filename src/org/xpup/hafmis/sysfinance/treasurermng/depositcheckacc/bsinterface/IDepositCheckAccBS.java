package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IDepositCheckAccBS {
  public Object[] findDepositCheckAccList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public Object[] findDepositCheckAccWindowInfo(List bdcList,List bcaList,Pagination pagination,SecurityInfo securityInfo) throws Exception;
}
