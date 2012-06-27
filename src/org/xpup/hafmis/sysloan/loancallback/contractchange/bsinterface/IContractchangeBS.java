package org.xpup.hafmis.sysloan.loancallback.contractchange.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.contractchange.dto.ContractchangeDTO;

public interface IContractchangeBS {
  public List queryContractchangeList(Pagination pagination,SecurityInfo securityInfo)throws BusinessException,Exception;
  public ContractchangeDTO printContractchangeDTO(String id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public void updatePl101_1(String id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public void updatePl101_2(String id,SecurityInfo securityInfo) throws Exception, BusinessException;
}
