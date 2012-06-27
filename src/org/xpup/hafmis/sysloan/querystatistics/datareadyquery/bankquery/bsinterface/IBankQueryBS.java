package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.bsinterface;

import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto.BankQueryDTO;

public interface IBankQueryBS {

  public List findBankQueryList(Pagination pagination, List officeList,
      List bankList) throws Exception;
  
  public List queryBankQueryListCount_wsh(Pagination pagination, List officeList,
      List bankList) throws Exception;

  public BankQueryDTO findBankQueryInfo(String id) throws Exception;
  
  public String getUserRealName(String name) throws Exception;
}
