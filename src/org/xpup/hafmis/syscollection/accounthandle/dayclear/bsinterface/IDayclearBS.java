package org.xpup.hafmis.syscollection.accounthandle.dayclear.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IDayclearBS {
  public String getBizDate(final String username,final String userIp );
  public List getBankInfoList(String username);
  public List getBankInfoList(String username,String opSystemType);
  public void updateBizDate_jj(String[] rowArray,SecurityInfo securityInfo)throws Exception,BusinessException;
}
