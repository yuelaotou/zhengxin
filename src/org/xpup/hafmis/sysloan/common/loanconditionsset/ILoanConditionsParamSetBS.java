package org.xpup.hafmis.sysloan.common.loanconditionsset;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public interface ILoanConditionsParamSetBS {
  // 1.是否有贷款资格 返回boolean
  boolean isCanSysLoan(LoanapplyNewAF loanapplyaf, SecurityInfo securityInfo)
      throws BusinessException;

  boolean isCanSysLoanTb(LoanapplyTbNewAF loanapplyTbNewAF,
      SecurityInfo securityInfo) throws BusinessException;

  // 2.可贷最大金额 返回BigDecimal
  BigDecimal canSysLoanMoney(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException;

  int canSysLoanlimit(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException;

  public BigDecimal canSysLoanlimit_1(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException;

  public void canLoancallback_ws(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws BusinessException;
}