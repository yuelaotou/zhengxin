package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.form.LoanBackByFundAF;

/**
 * @author ั๎นโ 20090303
 */
public interface ILoanBackByFundBS {
  public LoanBackByFundAF queryLoanBackByFund(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
