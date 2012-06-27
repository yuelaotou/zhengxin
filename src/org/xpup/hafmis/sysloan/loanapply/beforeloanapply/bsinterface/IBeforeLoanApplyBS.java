package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.form.BeforeLoanApplyShowAF;

public interface IBeforeLoanApplyBS  {
  public BeforeLoanApplyShowAF queryEmpInfo(String empid,String orgid,SecurityInfo securityInfo) throws Exception,BusinessException;
  public BigDecimal findMonthRate(String office,int year) throws Exception, BusinessException;
}
