package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;

public interface IBankCheckAccBS {
  public Object[] findParamExplainList(SecurityInfo securityInfo,String temp);
  public void saveBankCheckAccTa(BankCheckAccTaDTO bankCheckAccTaDTO,SecurityInfo securityInfo) throws Exception;
  public Object[] findBankCheckAccTbList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void deleteBankCheckAccTbList(String credenceId,SecurityInfo securityInfo) throws Exception;
  public void deleteBankCheckAccTbListAll(List list,SecurityInfo securityInfo) throws Exception;
  public BankCheckAccTaDTO findModifyInfo(String credenceId,SecurityInfo securityInfo) throws Exception;
  public void modifyInfo(BankCheckAccTaDTO bankCheckAccTaDTO,SecurityInfo securityInfo)throws Exception;
  public List findBankCheckAccPrintList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void importsBankCheckAcc(List bankCheckAccImpContent,String office, SecurityInfo securityInfo)throws Exception, BusinessException;
  public String checkSubjectCode(String subjectCode,String credenceType,SecurityInfo securityInfo);
  public String findBookSt(SecurityInfo securityInfo) throws Exception;
}
