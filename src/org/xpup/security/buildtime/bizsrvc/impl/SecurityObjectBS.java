package org.xpup.security.buildtime.bizsrvc.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Hibernate;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.BeanCopier;
import org.xpup.common.util.Pagination;
import org.xpup.security.buildtime.bizsrvc.IMaintainDataAccessBS;
import org.xpup.security.buildtime.bizsrvc.IMaintainMenuBS;
import org.xpup.security.buildtime.bizsrvc.IMaintainOperationBS;
import org.xpup.security.common.dao.DataAccessDAO;
import org.xpup.security.common.dao.MenuItemDAO;
import org.xpup.security.common.dao.OperationDAO;
import org.xpup.security.common.domain.DataAccess;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.Operation;

public class SecurityObjectBS implements IMaintainMenuBS, IMaintainOperationBS,
    IMaintainDataAccessBS {

  private MenuItemDAO menuItemDAO = null;

  private OperationDAO operationDAO = null;

  private DataAccessDAO dataAccessDAO = null;

  public void setDataAccessDAO(DataAccessDAO dataAccessDAO) {
    this.dataAccessDAO = dataAccessDAO;
  }

  public void setMenuItemDAO(MenuItemDAO menuItemDAO) {
    this.menuItemDAO = menuItemDAO;
  }

  public void setOperationDAO(OperationDAO operationDAO) {
    this.operationDAO = operationDAO;
  }

  // ========================================================

  public List loadMenuItemTree() {
    List menuItems = menuItemDAO.queryAllRoot();
    Iterator it = menuItems.iterator();
    while (it.hasNext()) {
      MenuItem menuItem = (MenuItem) it.next();
      initSubMenuItems(menuItem);
    }
    return menuItems;
  }

  private void initSubMenuItems(MenuItem menuItem) {
    Hibernate.initialize(menuItem.getSubMenuItems());
    int count = menuItem.getSubMenuItems().size();
    if (count == 0) {
      return;
    }
    Iterator it = menuItem.getSubMenuItems().iterator();
    while (it.hasNext()) {
      MenuItem subMenuItem = (MenuItem) it.next();
      initSubMenuItems(subMenuItem);
    }
  }

  public Serializable createMenuItem(MenuItem menuItem) {
    Validate.notNull(menuItem, "参数menuItem不能为空！");

    return menuItemDAO.insert(menuItem);
  }

  public void modifyMenuItem(MenuItem menuItem) {
    Validate.notNull(menuItem, "参数menuItem不能为空！");

    MenuItem old = menuItemDAO.queryById(menuItem.getId());
    BeanCopier.copyProperties(old, new String[] { "caption", "position",
        "target", "description", "url","opSystemType" }, menuItem);
  }

  public void removeMenuItem(Serializable menuItemId) throws BusinessException {
    menuItemDAO.deleteById(menuItemId);
  }

  public MenuItem findMenuItem(Serializable menuItemId)
      throws BusinessException {
    MenuItem menuItem = menuItemDAO.queryById(menuItemId);
    if (menuItem == null)
      throw new EntityNotFoundException("菜单不存在，或已经被删除!");
    return menuItem;
  }

  // ====================================================

  public List findAllOperationGroups() {
    return null;
  }

  public List findOperations(Pagination pagination) {

    String name = (String) pagination.getQueryCriterions().get("name");
    String innerName = (String) pagination.getQueryCriterions()
        .get("innerName");
    String description = (String) pagination.getQueryCriterions().get(
        "description");
    String group = (String) pagination.getQueryCriterions().get("group");

    String orderBy = (String) pagination.getOrderBy();
    if (orderBy == null)
      orderBy = "operation.id";
    OrderEnum order = pagination.getOrder();

    int count = operationDAO.queryCountByCriterions(name, innerName,
        description, group);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List operations = operationDAO.queryByCriterions(name, innerName,
        description, group, orderBy, order, start, pageSize);
    return operations;
  }

  public Operation findOperation(Serializable operationId) {
    Validate.notNull(operationId, "参数id不能为空！");

    return operationDAO.queryById(operationId);
  }

  public Serializable createOperation(Operation operation)
      throws BusinessException {
    Validate.notNull(operation, "参数operation不能为空！");
    return operationDAO.insert(operation);
  }

  public void modifyOperation(Operation operation) throws BusinessException {
    Validate.notNull(operation, "参数operation不能为空！");

    operationDAO.checkBeforeUpdate(operation);
    Operation old = operationDAO.queryById(operation.getId());
    BeanCopier.copyProperties(old, operation);
  }

  public void removeOperation(Serializable operationId)
      throws BusinessException {
    Validate.notNull(operationId, "参数id不能为空！");
    operationDAO.deleteById(operationId);
  }

  // =================================================================================

  public Serializable createDataAccess(DataAccess dataAccess)
      throws BusinessException {
    Validate.notNull(dataAccess, "参数dataAccess不能为空！");
    return dataAccessDAO.insert(dataAccess);
  }

  public DataAccess findDataAccess(Serializable id) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");
    return dataAccessDAO.queryById(id);
  }

  public List findDataAccesses(Pagination pagination) {
    String name = (String) pagination.getQueryCriterions().get("name");
    String innerName = (String) pagination.getQueryCriterions()
        .get("innerName");

    String orderBy = (String) pagination.getOrderBy();
    if (orderBy == null)
      orderBy = "dataAccess.id";
    OrderEnum order = pagination.getOrder();

    int count = dataAccessDAO.queryCountByCriterions(name, innerName);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List dataAccesses = dataAccessDAO.queryByCriterions(name, innerName, orderBy,
        order, start, pageSize);
    return dataAccesses;
  }

  public void modifyDataAccess(DataAccess dataAccess) throws BusinessException {
    Validate.notNull(dataAccess, "参数dataAccess不能为空！");

    dataAccessDAO.checkBeforeUpdate(dataAccess);
    DataAccess old = dataAccessDAO.queryById(dataAccess.getId());
    if(old == null)
      throw new EntityNotFoundException("数据资源不存在，或已经被删除！");
    BeanCopier.copyProperties(old, dataAccess);
  }

  public void removeDataAccess(Serializable id) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");
    dataAccessDAO.deleteById(id);
  }

}
