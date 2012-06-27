package org.xpup.hafmis.orgstrct.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;

public interface IMaintainEmployeeBS {

  public List findHafEmployees(Pagination pagination);

  public HafEmployee findHafEmployee(Serializable id) throws BusinessException;

  public Serializable createHafEmployee(HafEmployee hafEmployee)
      throws BusinessException;

  public void modifyHafEmployee(HafEmployee hafEmployee)
      throws BusinessException;

  public void removeHafEmployee(Serializable employeeId)
      throws BusinessException;

  public void changePassword(String username, String oldPassword,
      String newPassword) throws BusinessException;

  public void changePassword(String username, String newPassword)
      throws BusinessException;

  public void assignRolesToHafEmployee(Serializable employeeId, List roleIds)
      throws BusinessException;

  public void assignOperationsToHafEmployee(Serializable userId,
      List operationIds) throws BusinessException;

  public void assignMenuItemsToHafEmployee(Serializable userId, List menuItemIds)
      throws BusinessException;

  public List findDuRelationsAvailable(Serializable userId);

  public void assignDuRelationsToHafEmployee(List duRelations,
      Serializable userId) throws BusinessException;

  public List findOperationsAvailableByUserId(Serializable userId);

  public List findOperationsByUserId(Serializable userId);

  public List findRolesAvailableByUserId(Serializable userId);

  public List findRolesByUserId(Serializable userId);

}