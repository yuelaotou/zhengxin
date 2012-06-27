package org.xpup.hafmis.sysloan.credit.parameter.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.credit.parameter.form.CreditParamAF;

public interface ICreditParamBS {
  /**
   * ั๎นโ
   */
  public CreditParamAF getCreditParam() throws Exception, BusinessException;

  public void insertCreditParam(final CreditParamAF creditParamAF)
      throws BusinessException;
}
