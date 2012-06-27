package org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTaDTO;

public interface IAdvancepayloanBS {

  public AdvancepayloanTaDTO findAdvancepayloanTaDTO(String loadKouAcc, SecurityInfo securityInfo)throws BusinessException,Exception;
  
  public void insert(String contractId,String new_term,String type, SecurityInfo securityInfo)throws BusinessException,Exception;
  
  public List queryTbList(Pagination pagination,SecurityInfo securityInfo)throws BusinessException,Exception;
  
  public void delete(String id, SecurityInfo securityInfo)throws BusinessException,Exception;
}
