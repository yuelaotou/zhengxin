package org.xpup.hafmis.orgstrct.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.domain.HafRole;

public interface IMaintainRoleBS {

  public List findHafRoles(Pagination pagination);

  public HafRole findHafRole(Serializable id) throws BusinessException;

  public Serializable createHafRole(HafRole hafRole) throws BusinessException;

  public void modifyHafRole(HafRole hafRole) throws BusinessException;

  public void removeHafRole(Serializable hafRoleId) throws BusinessException;

  public void assignOperationsToHafRole(Serializable hafRoleId, List operationIds)
      throws BusinessException;

  public void assignMenuItemsToHafRole(Serializable hafRoleId, List menuItemIds)
      throws BusinessException;

  public void assignDrRelationsToHafRole(List drRelations, Serializable roleId)
  throws BusinessException;

  public List findDrRelationsAvailable(Serializable roleId);

  public List findOperationsAvailableByRoleId(Serializable roleId);

  public List findOperationsByRoleId(Serializable roleId);

}
