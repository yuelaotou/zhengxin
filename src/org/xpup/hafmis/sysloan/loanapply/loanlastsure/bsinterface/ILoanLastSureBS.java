package org.xpup.hafmis.sysloan.loanapply.loanlastsure.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;

/**
 * @author 王野 2007-09-27
 */
public interface ILoanLastSureBS {

  // 审批贷款列表
  public LoanLastSureShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  
  // 终审通过 update BorrowerAcc
  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // 撤消终审通过 update BorrowerAcc
  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  

  // 转换办事处
  public String changeOffice(String office) throws Exception;
}
