package org.xpup.hafmis.syscollection.collloanback.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanback.form.CollLoanbackTaAF;

public interface ICollLoanbackBS {
  public CollLoanbackTaAF findCollLoanbackTaList(Pagination pagination);
  public List FindCollectionBankId(String officeCode,List collBankList) throws Exception,BusinessException;
  public String getBackMode(String loanBankId) throws Exception;
  public void imports(List importList, SecurityInfo securityInfo) throws Exception;
  public List findExportList(String loanBankId,SecurityInfo securityInfo)throws Exception,BusinessException;
  public void kouMoney(String batchNum, Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  public Object[] findCollLoanbackTbList(Pagination pagination);
  public void collLoanbackDelete(String[] rowArray, SecurityInfo securityInfo)
  throws Exception;
  public List findCollLoanbackTcList(Pagination pagination) throws Exception;
  public BigDecimal getCollLoanbackCheckMoney(List listCount) throws Exception;
  public List findCollLoanbackTcCount(Pagination pagination) throws Exception;
  public String getCollBankDate(String loanBankId) throws Exception;
}
