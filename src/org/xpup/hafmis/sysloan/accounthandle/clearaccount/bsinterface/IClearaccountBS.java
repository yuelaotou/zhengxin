package org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceInfoAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;

public interface IClearaccountBS {
  // 扎账列表
  public ClearaccountAF queryClearaccountList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public String getMydate() throws Exception ;
  // 单笔扎账
  public String clearAccountInfo(SecurityInfo securityInfo, String[] rowArray,
      String plLoanReturnType) throws BusinessException;

  // 全部扎账
  public String clearAccountList(SecurityInfo securityInfo,
      String plLoanReturnType,Pagination pagination) throws BusinessException;
  
  public ClearAccountBalanceInfoAF findClearAccountBalanceInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  public ClearaccountAF findClearaccountMXList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException ;
  public String getLoanBankName(String loanBankId) throws Exception;
  public List findClearaccountMXPrint(Pagination pagination,
      SecurityInfo securityInfo)throws Exception;
}
