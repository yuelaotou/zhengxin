package org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractPrintDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;


public interface IRelieveContractBS {
  public RelieveContractTaDTO findRelieveContractTaInfo(String loadKouAcc,SecurityInfo securityInfo) throws Exception,BusinessException;
  public void saveRelieveContractTa(String contractId,SecurityInfo securityInfo) throws Exception;
  public List findRelieveContractTbList(Pagination pagination,List loanbankList) throws Exception;
  public void deleteRelieveContractTb(String contractId,SecurityInfo securityInfo) throws Exception;
  public RelieveContractPrintDTO findRelieveContractTbPrintInfo(String contractId) throws Exception;
}
