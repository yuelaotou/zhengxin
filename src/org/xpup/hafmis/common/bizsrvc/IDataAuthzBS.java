package org.xpup.hafmis.common.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.domain.enums.BizTypeEnum;

public interface IDataAuthzBS {

  public List findBizDataLimitsForQuery(String username, BizTypeEnum bizType)
      throws BusinessException;

  public void decideForOperation(String username, BizTypeEnum bizType,
      Serializable officeId, Serializable operatorId) throws BusinessException;

}
