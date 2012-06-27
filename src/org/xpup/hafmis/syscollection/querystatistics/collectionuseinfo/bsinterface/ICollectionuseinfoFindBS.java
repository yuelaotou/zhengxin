package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.dto.DefineFormulaDto;

public interface ICollectionuseinfoFindBS {
  public List findCollectionuseinfo(SecurityInfo securityInfo,String officeCode,String collectionBankId,String queryTime)throws Exception,BusinessException;
  public List findAccountList() throws Exception;
  public List findAA400List() throws Exception;
  public void saveCollectionuseParamInfo(DefineFormulaDto defineFormulaDto,SecurityInfo securityInfo) throws Exception;
  public String is_CodeIn_WL(final String code, final String states,final SecurityInfo securityInfo) throws BusinessException;
}
