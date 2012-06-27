package org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public interface ILoancallbackBS{
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination) throws Exception;
  public List findCallbackList(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public void deleteCallbackInfos(String headId,SecurityInfo securityInfo) throws Exception;
  public void callbackCallbackInfo(String headId,SecurityInfo securityInfo,String contractid) throws Exception;
  public void deleteCallbackInfoByBank(String headId,SecurityInfo securityInfo)throws Exception;
  public LoancallbackTbDTO findCallbackListTotal(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public LoancallbackTaAF findCallbackInfoMX(Pagination pagination) throws Exception;
  public String addCallbackInfo(LoancallbackTaAF af,SecurityInfo securityInfo,String matter) throws Exception;
  public Integer adCallbackBatch(List importList,SecurityInfo securityInfo)throws Exception;
  public String addCallbackInfoByLoanBankId(Pagination pagination,String contractId,SecurityInfo securityInfo) throws Exception;
  public LoancallbackTaAF findCallbacklistByLoanBank(Pagination pagination) throws Exception;
  public LoancallbackTaAF findPrintCallbackInfo(String headId);
  public List findCallbackBatchMX(Pagination pagination)throws Exception;;
  public ShouldBackListDTO findCallbackBatchMXTotal(Pagination pagination);
  public BigDecimal getCorpusInterest(Pagination pagination, SecurityInfo securityInfo)throws Exception;
  public List getYearMonthList(String loanRepayDay,String contractId,SecurityInfo securityInfo)throws Exception;
  public List findCallbackBatchMXPrint(Pagination pagination)throws Exception;
  public LoancallbackTaAF getLoancallbackTaAF(Pagination pagination,
      SecurityInfo securityInfo)throws Exception;
  // ÎâºéÌÎÐÞ¸Ä//2007-3-12
  public LoancallbackTaAF findShouldLoancallbackInfo_wuht(String loanMode,String sumInterest,String corpusinterest,String monthYear,
      String contractId,String deadLine,String sumCorpus, 
      SecurityInfo securityInfo) throws Exception;
  // ÎâºéÌÎÐÞ¸Ä//2007-3-12
  public String partAheadInfo_wsh(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws Exception ;
  public String selectPL202_BankId_wsh(String headId);
  public String someBackTime(String contractId,String salary) throws Exception ;
}