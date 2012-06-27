package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;

public interface IDataAccessControlBS {

  public List findDuRelationsByUserId(Serializable userId);

  public List findDrRelationsByUserId(Serializable userId);

  public List findDrRelationsByRoleId(Serializable roleId);

  public List findDataAccessesUnusedByUserId(Serializable userId);

  public List findDataAccessesUnusedByRoleId(Serializable roleId);

  public void assignDuRelationsToUser(List duRelations, Serializable userId)
      throws BusinessException;

  public void assignDrRelationsToRole(List drRelations, Serializable roleId)
      throws BusinessException;

}
