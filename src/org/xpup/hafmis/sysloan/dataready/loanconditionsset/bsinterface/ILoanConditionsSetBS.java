package org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;

public interface ILoanConditionsSetBS {
  public LoanConditionsSetDTO findLoanConditionsSetInfo(String office) throws Exception;
  public void saveLoanConditionsSetInfo(LoanConditionsSetDTO loanConditionsSetDTO,SecurityInfo securityInfo) throws Exception;
}
