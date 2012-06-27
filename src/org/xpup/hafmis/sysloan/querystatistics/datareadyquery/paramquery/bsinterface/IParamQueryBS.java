package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.bsinterface;

import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto.ParamQueryDTO;

public interface IParamQueryBS {
  public ParamQueryDTO findParamQueryInfo(String loanBankId) throws Exception;
}
