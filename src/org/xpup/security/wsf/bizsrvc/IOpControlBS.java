package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;

public interface IOpControlBS {

  /**
   * 判断用户是否拥有相应的操作权限。如果有权限，正常返回。如果没有，抛出异常。
   * 
   * @param username 用户登录名
   * @param opInnerName 操作的内部名
   * @throws BusinessException 如果用户没有相应的权限，抛出本异常。
   */
  public void decide(String username, String opInnerName)
      throws BusinessException;

  /**
   * 为指定角色分配操作权限。
   * 
   * @param roleId 角色的ID
   * @param operationIds 操作ID的list
   */
  public void assignOperationsToRole(Serializable roleId, List operationIds)
      throws BusinessException;
  
  /**
   * 为指定用户分配操作权限。
   * 
   * @param userId 角色的ID
   * @param operationIds 操作ID的list
   */
  public void assignOperationsToUser(Serializable userId, List operationIds)
      throws BusinessException;

  public List findOperationsUnusedByUserId(Serializable userId);

  public List findOperationsByUserId(Serializable userId);

  public List findOperationsUnusedByRoleId(Serializable roleId);

  public List findOperationsByRoleId(Serializable roleId);
  public boolean checkIsOrg();

  public boolean checkIsCenter();
//判断是否有单位版
  public boolean checkHaveOrg();
}
