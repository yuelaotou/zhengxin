package org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;


public interface IBankImportsBS {
  public List findBankCallbackList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public BatchShouldBackListDTO findTotalBankCallback(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public String addBankImportsInfo(List importList,SecurityInfo securityInfo) throws Exception;
  public void updateBankImportsInfo(List importList,SecurityInfo securityInfo)throws Exception;
  public String getBackMode(String loanBankId) throws Exception;
  public void loanCallback(String tailId,SecurityInfo securityInfo)throws Exception;
  public void deleteAllCallbackInfo(String tailId,SecurityInfo securityInfo)throws Exception;
  public String getLoanBankName(String loanBankId) throws Exception;
}