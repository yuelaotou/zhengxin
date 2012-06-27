package org.xpup.hafmis.sysfinance.treasurermng.accountclear.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IAccountClearBS {
  public List findSummaryList(SecurityInfo securityInfo);
  public Object[] findAccountClearList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void account(String[] rowArray,SecurityInfo securityInfo) throws Exception;
}
