package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;

public interface IBeforeLoanAdvisoryBS  {

  public BeforeLoanAdvisoryShowAF queryEmpInfo(Pagination pagination, 
      SecurityInfo securityInfo,BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF) throws Exception,BusinessException;
  public BeforeLoanAdvisoryShowAF querySpouseInfo(Pagination pagination, SecurityInfo securityInfo,
      BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF) throws Exception;
  public BeforeLoanAdvisoryShowAF queryLoanInfo(BeforeLoanAdvisoryShowAF  
      beforeLoanAdvisoryShowAF,String housetype,String totalPrice) throws Exception,BusinessException; 
  public String findMonthRate(String office, String loantimeLimit,
      String loanMood) throws BusinessException;
  BeforeLoanAdvisoryShowAF queryIputLoanInfo(BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF,
      String inputLoanMoney,String housetype) throws Exception;
}
