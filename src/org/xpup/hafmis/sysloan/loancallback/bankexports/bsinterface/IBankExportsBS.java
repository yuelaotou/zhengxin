package org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;

public interface IBankExportsBS {
  public String getPL600Num(String office,String bizDate)throws Exception;
  public String getPL601Num(String office,String bizDate)throws Exception;
  public void updatePL600Num(String office,String bizDate,String num)throws Exception;
  public void deletePL601Num(String office,String bizDate,String num)throws Exception;
  public void insertPL601Num(String office,String bizDate,String num)throws Exception;
  public List getNum_yg(String bankid)throws Exception;
  public List getYearMonthList(SecurityInfo securityInfo);
  public List findBankCallbackList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public String getBackMode(String loanBankId)throws Exception;
  public List findExportList(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public void deleteTailInfo(String tailId,SecurityInfo securityInfo)throws Exception;
  public void deleteTailList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public String createDataBankCallbackList(Pagination pagination,SecurityInfo securityInfo,String type_gjp)throws Exception;
  public List getPrintList(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public BatchShouldBackListDTO findTotalBankCallback(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public String getNamePara() throws Exception;
}