package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.bsinterface;

import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.dto.AheadReturnParamQueryDTO;

public interface IAheadReturnParamQueryBS {
  public AheadReturnParamQueryDTO findAheadReturnParamQueryInfo(String loanBankId) throws Exception;
}
