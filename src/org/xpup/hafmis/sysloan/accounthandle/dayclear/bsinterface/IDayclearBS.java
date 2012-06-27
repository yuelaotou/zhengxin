package org.xpup.hafmis.sysloan.accounthandle.dayclear.bsinterface;

import java.util.List;

public interface IDayclearBS {
  public String getBizDate(final String username,final String userIp );
  public List getBankInfoList(String username);
}
