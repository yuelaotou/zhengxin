package org.xpup.hafmis.sysloan.credit.parameter.business;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.common.dao.CreditParamDAO;
import org.xpup.hafmis.sysloan.credit.parameter.bsinterface.ICreditParamBS;
import org.xpup.hafmis.sysloan.credit.parameter.form.CreditParamAF;

public class CreditParamBS implements ICreditParamBS {
  private CreditParamDAO creditParamDAO = null;

  public void setCreditParamDAO(CreditParamDAO creditParamDAO) {
    this.creditParamDAO = creditParamDAO;
  }

  public CreditParamAF getCreditParam() throws Exception, BusinessException {
    return creditParamDAO.getCreditParam();
  }

  public void insertCreditParam(CreditParamAF creditParamAF)
      throws BusinessException {
    creditParamDAO.deleteCreditParam();
    creditParamDAO.insertCreditParam(creditParamAF);
  }
}
