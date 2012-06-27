package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTbDTO;

public interface IBadDebtDestroyBS{
  
  public List getYearMonthList(String loanRepayDay,String contractId,SecurityInfo securityInfo)throws Exception;
  public BadDebtDestroyTaAFDTO findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  public String addCallbackInfo(BadDebtDestroyTaAFDTO af,SecurityInfo securityInfo) throws Exception;
  public void deleteCallbackInfoByBank(String headId,SecurityInfo securityInfo)throws Exception;
  public Integer adCallbackBatch(List importList,SecurityInfo securityInfo)throws Exception;
  public BadDebtDestroyTaAFDTO findCallbacklistByLoanBank(Pagination pagination) throws Exception;
  public String addCallbackInfoByLoanBankId(String headId,String contractId,SecurityInfo securityInfo) throws Exception;
  public BadDebtDestroyTbDTO findCallbackListTotal(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public List findCallbackList(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public void deleteCallbackInfos(String headId,SecurityInfo securityInfo) throws Exception;
  public void callbackCallbackInfo(String headId,SecurityInfo securityInfo) throws Exception;
  public BadDebtDestroyTaAFDTO findPrintCallbackInfo(String headId);
  public BadDebtDestroyTaAFDTO findCallbackInfoMX(Pagination pagination) throws Exception;
}