package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.security.common.domain.MenuItem;

public interface IMenuControlBS {

  public List loadMenuItemTree();

  public List findAllMenuItemsByUserId(Serializable userId);

  public List findAllMenuItemsByRoleId(Serializable roleId);

  public MenuItem findMenuItem(Serializable id) throws BusinessException;

  /**
   * 返回用户所拥有的所有根菜单。
   * 
   * @param username 用户登录名
   * @return 根菜单列表
   */
  public String getMenu(final String username, final Serializable parentId)throws Exception;  
  
  
  public List findAllRootMenu(String username);

  /**
   * 返回父菜单下用户所拥有的所有子菜单。
   * 
   * @param username 用户登录名
   * @param parentId 父菜单ID
   * @return 子菜单列表
   */
  
  public String getMenu(final String username,
      final Serializable parentId, String url)throws Exception;  
  public List findAllMenu(String username, String parentId);

  /**
   * 将指定的菜单项分配给指定角色。
   * 
   * @param roleId 角色ID
   * @param menuIds 菜单项ID列表
   */
  public void assignMenuItemsToRole(Serializable roleId, List menuItemIds)
      throws BusinessException;

  /**
   * 将指定的菜单项分配给指定用户。
   * 
   * @param roleId 用户ID
   * @param menuIds 菜单项ID列表
   */
  public void assignMenuItemsToUser(Serializable userId, List menuItemIds)
      throws BusinessException;

  
  public MenuItem queryMenuItem(final String username,final String parentId);
}