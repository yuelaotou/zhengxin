package org.xpup.security.buildtime.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.security.common.domain.Operation;

public interface IMaintainOperationBS {

  public List findOperations(Pagination pagination);

  public Serializable createOperation(Operation operation)
      throws BusinessException;

  public void modifyOperation(Operation operation) throws BusinessException;

  public void removeOperation(Serializable operationId)
      throws BusinessException;

  public Operation findOperation(Serializable operationId)
      throws BusinessException;

  public List findAllOperationGroups();

}
