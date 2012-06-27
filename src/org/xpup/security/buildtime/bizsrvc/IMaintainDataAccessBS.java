package org.xpup.security.buildtime.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.security.common.domain.DataAccess;

public interface IMaintainDataAccessBS {

  public List findDataAccesses(Pagination pagination);

  public DataAccess findDataAccess(Serializable id) throws BusinessException;

  public Serializable createDataAccess(DataAccess dataAccess)
      throws BusinessException;

  public void modifyDataAccess(DataAccess dataAccess) throws BusinessException;

  public void removeDataAccess(Serializable operationId)
      throws BusinessException;
}
