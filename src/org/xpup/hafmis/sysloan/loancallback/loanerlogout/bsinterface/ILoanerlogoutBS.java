package org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto.LoanerlogoutTaDTO;



public interface ILoanerlogoutBS {
  public void findLoanerlogoutTaExit(String loanKouAcc,List loanBankList) throws Exception,BusinessException;
  public LoanerlogoutTaDTO findLoanerlogoutTaInfo(String loanKouAcc,List loanbankList) throws Exception,BusinessException;
  public void findLoanerlogouAvailable(String contractId) throws Exception, BusinessException;
  public void saveLoanerlogouTa(String contractId,SecurityInfo securityInfo)throws Exception;
  public List findLoanerlogoutTbList(Pagination pagination,List loanbankList) throws Exception;
  public void trunLogoutLoanerlogout(String contractId, SecurityInfo securityInfo)throws Exception,BusinessException;
  public List findLoanerlogoutTbPrintList(Pagination pagination,List loanbankList) throws Exception;
}
