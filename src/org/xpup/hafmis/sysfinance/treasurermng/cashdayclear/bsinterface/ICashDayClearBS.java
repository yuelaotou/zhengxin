package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface;



import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;

public interface ICashDayClearBS {
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp);
  public String findCredenceDateByOffice(String office,String credenceType,SecurityInfo securityInfo);
  public void saveCashDayClearTa(CashDayClearTaDTO cashDayClearTaDTO,String credenceType,SecurityInfo securityInfo) throws Exception;
  public Object[] findCashDayClearTbList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception;
  public void cashDayClearTaTransfers(String[] rowArray,String credenceType,SecurityInfo securityInfo) throws Exception;
  public Object[] findCashDayClearTcList(String credenceType,Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void deleteCashDayClearTcList(String credenceId,String credenceType,SecurityInfo securityInfo) throws Exception;
  public CashDayClearTaDTO findModifyInfo(String credenceId,SecurityInfo securityInfo) throws Exception;
  public void modifyInfo(CashDayClearTaDTO newCashDayClearTaDTO,CashDayClearTaDTO oldCashDayClearTaDTO,String credenceType,SecurityInfo securityInfo)throws Exception;
  public List findCashDayClearTcPrintList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception;
  public String findBookSt(SecurityInfo securityInfo) throws Exception;
  public void deleteCashDayClearTcListAll(List list,SecurityInfo securityInfo,String credenceType) throws Exception;
  public List findCashDayClearTcPrintList_wsh(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception;
}
