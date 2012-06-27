package org.xpup.hafmis.orgstrct.bizsrvc.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.hibernate.Hibernate;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.enums.SexEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.BeanCopier;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainEmployeeBS;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOrgUnitBS;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainRoleBS;
import org.xpup.hafmis.orgstrct.dao.HafEmployeeDAO;
import org.xpup.hafmis.orgstrct.dao.HafRoleDAO;
import org.xpup.hafmis.orgstrct.dao.OrgUnitPropTemplateDAO;
import org.xpup.hafmis.orgstrct.dao.OrgUnitPropertyDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.OuptItemDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.daoDW.OrgUnitPropTemplateDAODW;
import org.xpup.hafmis.orgstrct.daoDW.OrgUnitPropertyDAODW;
import org.xpup.hafmis.orgstrct.daoDW.OrganizationUnitDAODW;
import org.xpup.hafmis.orgstrct.daoDW.OuptItemDAODW;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.domain.HafRole;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.OrgUnitUtils;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;
import org.xpup.hafmis.orgstrct.dto.OuptDTO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.DataAccess;
import org.xpup.security.wsf.bizsrvc.IDataAccessControlBS;
import org.xpup.security.wsf.bizsrvc.IMenuControlBS;
import org.xpup.security.wsf.bizsrvc.IOpControlBS;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class OrgStructureBS implements IMaintainEmployeeBS, IMaintainRoleBS,
    IMaintainOuptBS, IMaintainOrgUnitBS {
  
  private IUserControlBS userControlBS = null;

  private IMenuControlBS menuControlBS = null;

  private IOpControlBS opControlBS = null;

  private IDataAccessControlBS dataAccessControlBS = null;

  private HafEmployeeDAO hafEmployeeDAO = null;

  private HafRoleDAO hafRoleDAO = null;

  private OrgUnitPropTemplateDAO orgUnitPropTemplateDAO = null;

  private OuptItemDAO ouptItemDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private OrgUnitPropertyDAO orgUnitPropertyDAO = null;

  private SecurityDAO securityDAO = null;

  private OrganizationUnitDAODW organizationUnitDAODW = null;
  
  private OrgUnitPropertyDAODW orgUnitPropertyDAODW=null;
  
  private OrgUnitPropTemplateDAODW orgUnitPropTemplateDAODW=null;

  private OuptItemDAODW ouptItemDAODW = null;
  
  public void setOuptItemDAODW(OuptItemDAODW ouptItemDAODW) {
    this.ouptItemDAODW = ouptItemDAODW;
  }

  public void setOrgUnitPropTemplateDAODW(
      OrgUnitPropTemplateDAODW orgUnitPropTemplateDAODW) {
    this.orgUnitPropTemplateDAODW = orgUnitPropTemplateDAODW;
  }

  public void setOrganizationUnitDAODW(OrganizationUnitDAODW organizationUnitDAODW) {
    this.organizationUnitDAODW = organizationUnitDAODW;
  }

  public void setOrgUnitPropertyDAODW(OrgUnitPropertyDAODW orgUnitPropertyDAODW) {
    this.orgUnitPropertyDAODW = orgUnitPropertyDAODW;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setDataAccessControlBS(IDataAccessControlBS dataAccessControlBS) {
    this.dataAccessControlBS = dataAccessControlBS;
  }

  public void setOrgUnitPropertyDAO(OrgUnitPropertyDAO orgUnitPropertyDAO) {
    this.orgUnitPropertyDAO = orgUnitPropertyDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgUnitPropTemplateDAO(
      OrgUnitPropTemplateDAO orgUnitPropTemplateDAO) {
    this.orgUnitPropTemplateDAO = orgUnitPropTemplateDAO;
  }

  public void setOuptItemDAO(OuptItemDAO ouptItemDAO) {
    this.ouptItemDAO = ouptItemDAO;
  }

  public void setHafEmployeeDAO(HafEmployeeDAO hafEmployeeDAO) {
    this.hafEmployeeDAO = hafEmployeeDAO;
  }

  public void setHafRoleDAO(HafRoleDAO hafRoleDAO) {
    this.hafRoleDAO = hafRoleDAO;
  }

  public void setMenuControlBS(IMenuControlBS menuControlBS) {
    this.menuControlBS = menuControlBS;
  }

  public void setUserControlBS(IUserControlBS userControlBS) {
    this.userControlBS = userControlBS;
  }

  public void setOpControlBS(IOpControlBS opControlBS) {
    this.opControlBS = opControlBS;
  }

  // ==============================================================

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#findHafEmployees(org.xpup.common.util.Pagination)
   */
  public List findHafEmployees(Pagination pagination) {

    Validate.notNull(pagination, "参数pagination不能为空！");

    String username = (String) pagination.getQueryCriterions().get("username");
    String realName = (String) pagination.getQueryCriterions().get("realName");
    String email = (String) pagination.getQueryCriterions().get("email");
    SexEnum sex = (SexEnum) pagination.getQueryCriterions().get("sex");
    DutyEnum duty = (DutyEnum) pagination.getQueryCriterions().get("duty");
    Boolean enabled = (Boolean) pagination.getQueryCriterions().get("enabled");
    Boolean locked = (Boolean) pagination.getQueryCriterions().get("locked");

    String orderBy = pagination.getOrderBy();
    OrderEnum order = pagination.getOrder();

    int count = hafEmployeeDAO.queryCountByCriterions(username, realName,
        email, sex, duty, enabled, locked);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List employees = hafEmployeeDAO.queryByCriterions(username, realName,
        email, sex, duty, enabled, locked, orderBy, order, start, pageSize);
    // 吴洪涛 2008－3－14 角色
    try {
      if (employees != null && employees.size() > 0) {
        for (int i = 0; i < employees.size(); i++) {
          HafEmployee hafEmployee = (HafEmployee) employees.get(i);
          if (hafEmployee.getId() != null) {
            List listName = hafEmployeeDAO.queryNameById(hafEmployee.getId()
                .toString());
            String hafEmployeeName = "";
            if (listName != null && listName.size() > 0) {
              for (int j = 0; j < listName.size(); j++) {
                hafEmployeeName = hafEmployeeName + (String) listName.get(j)
                    + ",";
              }
            }
            if (hafEmployeeName != "")
              hafEmployeeName = hafEmployeeName.substring(0, hafEmployeeName
                  .lastIndexOf(","));
            hafEmployee.setRolesName(hafEmployeeName);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 吴洪涛 2008－3－14 角色
    return employees;
  }

  public HafEmployee findHafEmployee(Serializable id) {

    HafEmployee employee = (HafEmployee) hafEmployeeDAO.queryById(id);
    Hibernate.initialize(employee.getOrganizationUnit());
    return employee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#createHafEmployee(org.xpup.hafmis.orgstrct.domain.HafEmployee)
   */
  public Serializable createHafEmployee(HafEmployee hafEmployee)
      throws BusinessException {
    Validate.notNull(hafEmployee, "参数 hafEmployee 不能为空");
    Validate.notNull(hafEmployee.getUsername(),
        "hafEmployee.getUsername() 不能为空");
    Validate.notNull(hafEmployee.getPassword(),
        "hafEmployee.getPassword() 不能为空");
    Validate.notEmpty(hafEmployee.getUsername(),
        "hafEmployee.getUsername() 不能为空");

    String password = userControlBS.encodedPassword(hafEmployee.getPassword(),
        hafEmployee);
    hafEmployee.setPassword(password);

    OrganizationUnit orgUnit = organizationUnitDAO.queryById(hafEmployee
        .getOrganizationUnit().getId());
    OrgUnitUtils ouUtils = new OrgUnitUtils(orgUnit);
    hafEmployee.setOfficeId((String) ouUtils.getOfficeId());
    hafEmployee.setDepartmentId((String) ouUtils.getDepartmentId());

    return hafEmployeeDAO.insert(hafEmployee);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#modifyHafEmployee(org.xpup.hafmis.orgstrct.domain.HafEmployee)
   */
  public void modifyHafEmployee(HafEmployee hafEmployee)
      throws BusinessException {

    hafEmployeeDAO.checkBeforeUpdate(hafEmployee);
    HafEmployee e = (HafEmployee) hafEmployeeDAO.queryById(hafEmployee.getId());
    BeanCopier.copyProperties(e, new String[] { "username", "realName", "sex",
        "duty", "email", "description", "enabled", "bizDate", "plbizDate",
        "fbizDate" }, hafEmployee);
    e.setOrganizationUnit(hafEmployee.getOrganizationUnit());
    OrganizationUnit orgUnit = organizationUnitDAO.queryById(hafEmployee
        .getOrganizationUnit().getId());
    OrgUnitUtils ouUtils = new OrgUnitUtils(orgUnit);
    e.setOfficeId((String) ouUtils.getOfficeId());
    e.setDepartmentId((String) ouUtils.getDepartmentId());
    e.setCheckMoney(hafEmployee.getCheckMoney());
    userControlBS.removeUserFromCache(hafEmployee.getUsername());

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#removeHafEmployee(java.io.Serializable)
   */
  public void removeHafEmployee(Serializable employeeId)
      throws BusinessException {
    hafEmployeeDAO.deleteById(employeeId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#changePassword(java.lang.String,
   *      java.lang.String, java.lang.String)
   */
  public void changePassword(String username, String oldPassword,
      String newPassword) throws BusinessException {
    userControlBS.changePassword(username, oldPassword, newPassword);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#changePassword(java.lang.String,
   *      java.lang.String)
   */
  public void changePassword(String username, String newPassword)
      throws BusinessException {
    userControlBS.changePassword(username, newPassword);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.hafmis.orgstrct.bizsrvc.impl.IMaintainEmployeeBS#assignRolesToHafEmployee(java.io.Serializable,
   *      java.util.List)
   */
  public void assignRolesToHafEmployee(Serializable employeeId, List roleIds)
      throws BusinessException {
    userControlBS.assignRolesToUser(employeeId, roleIds);
  }

  public void assignOperationsToHafEmployee(Serializable userId,
      List operationIds) throws BusinessException {
    opControlBS.assignOperationsToUser(userId, operationIds);

  }

  public void assignMenuItemsToHafEmployee(Serializable userId, List menuItemIds)
      throws BusinessException {
    menuControlBS.assignMenuItemsToUser(userId, menuItemIds);
  }

  // ====================================================================

  public List findHafRoles(Pagination pagination) {

    Validate.notNull(pagination, "参数pagination不能为空！");

    String name = (String) pagination.getQueryCriterions().get("name");
    String description = (String) pagination.getQueryCriterions().get(
        "description");

    String orderBy = pagination.getOrderBy();
    OrderEnum order = pagination.getOrder();

    int count = hafRoleDAO.queryCountByCriterions(name, description);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List roles = hafRoleDAO.queryByCriterions(name, description, orderBy,
        order, start, pageSize);
    return roles;
  }

  public Serializable createHafRole(HafRole hafRole) throws BusinessException {

    OrganizationUnit orgUnit = organizationUnitDAO.queryById(hafRole
        .getOrganizationUnit().getId());
    OrgUnitUtils ouUtils = new OrgUnitUtils(orgUnit);
    hafRole.setOfficeId((String) ouUtils.getOfficeId());
    hafRole.setDepartmentId((String) ouUtils.getDepartmentId());

    return hafRoleDAO.insert(hafRole);
  }

  public void modifyHafRole(HafRole hafRole) throws BusinessException {

    HafRole old = (HafRole) hafRoleDAO.queryById(hafRole.getId());
    hafRoleDAO.checkBeforeUpdate(hafRole);
    BeanCopier.copyProperties(old, new String[] { "name", "description" },
        hafRole);
    old.setOrganizationUnit(hafRole.getOrganizationUnit());

    OrganizationUnit orgUnit = organizationUnitDAO.queryById(hafRole
        .getOrganizationUnit().getId());
    OrgUnitUtils ouUtils = new OrgUnitUtils(orgUnit);
    old.setOfficeId((String) ouUtils.getOfficeId());
    old.setDepartmentId((String) ouUtils.getDepartmentId());
  }

  public void removeHafRole(Serializable hafRoleId) throws BusinessException {
    try {
      List list = hafRoleDAO.queryRelationByhafRoleId(hafRoleId.toString());
      if (list.size() != 0) {
        throw new BusinessException("该角色已经被分配使用，请先删除对应应用再做删除业务！！");
      }
      hafRoleDAO.deleteById(hafRoleId);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void assignOperationsToHafRole(Serializable hafRoleId,
      List operationIds) throws BusinessException {

    opControlBS.assignOperationsToRole(hafRoleId, operationIds);
  }

  public void assignMenuItemsToHafRole(Serializable hafRoleId, List menuItemIds)
      throws BusinessException {

    menuControlBS.assignMenuItemsToRole(hafRoleId, menuItemIds);
  }

  public void assignDrRelationsToHafRole(List drRelations, Serializable roleId)
      throws BusinessException {

    dataAccessControlBS.assignDrRelationsToRole(drRelations, roleId);
  }

  public List findDrRelationsAvailable(Serializable roleId) {

    List all = dataAccessControlBS.findDrRelationsByRoleId(roleId);
    all.addAll(getDrRelations(dataAccessControlBS
        .findDataAccessesUnusedByRoleId(roleId)));
    Collections.sort(all);
    return all;
  }

  private List getDrRelations(List dataAccesses) {
    List list = new ArrayList();
    for (int i = 0; i < dataAccesses.size(); i++) {
      list.add(((DataAccess) dataAccesses.get(i)).createDrRelation());
    }
    return list;
  }

  public HafRole findHafRole(Serializable id) throws BusinessException {
    HafRole role = (HafRole) hafRoleDAO.queryById(id);
    if (role == null)
      throw new EntityNotFoundException("角色不存在，或已经被删除。");
    return role;
  }

  public List findOperationsAvailableByRoleId(Serializable roleId) {

    return opControlBS.findOperationsUnusedByRoleId(roleId);
  }

  public List findOperationsByRoleId(Serializable roleId) {

    return opControlBS.findOperationsByRoleId(roleId);
  }

  // ======================================

  public Serializable createOupt(OrgUnitPropTemplate oupt,SecurityInfo securityInfo)
      throws BusinessException {
    Validate.notNull(oupt, "参数oupt不能为空！");
    Serializable id = orgUnitPropTemplateDAO.insert(oupt);
    try{
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          orgUnitPropTemplateDAODW.checkOupt(oupt);    
          orgUnitPropTemplateDAODW.insertDW(id,oupt);    
        }
      }
    }catch(BusinessException be){
      throw be;
    }
    return id;
  }

  public Serializable createOuptItem(OuptItem ouptItem,SecurityInfo securityInfo)
      throws BusinessException {
    Validate.notNull(ouptItem, "参数ouptItem不能为空！");
    Serializable id = ouptItemDAO.insert(ouptItem);
    try{
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          ouptItemDAODW.checkBeforeCreate(ouptItem);    
          ouptItemDAODW.insertDW(id,ouptItem);    
        }
      }
    }catch(BusinessException be){
      throw be;
    }
    return id;
  }

  public OrgUnitPropTemplate findOupt(Serializable id) throws BusinessException {
    OrgUnitPropTemplate oupt = orgUnitPropTemplateDAO.queryById(id);
    if (oupt == null) {
      throw new EntityNotFoundException("模板不存在，或已经被删除！");
    }
    return oupt;
  }

  public OuptItem findOuptItem(Serializable id) throws BusinessException {
    OuptItem ouptItem = ouptItemDAO.queryById(id);
    if (ouptItem == null) {
      throw new EntityNotFoundException("模板项不存在，或已经被删除！");
    }
    return ouptItem;
  }

  public List findOupts(Pagination pagination) {
    Validate.notNull(pagination, "参数pagination不能为空！");

    String name = (String) pagination.getQueryCriterions().get("name");
    String description = (String) pagination.getQueryCriterions().get(
        "description");
    String orderBy = pagination.getOrderBy();
    OrderEnum order = pagination.getOrder();

    int count = orgUnitPropTemplateDAO
        .queryCountByCriterions(name, description);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List oupts = orgUnitPropTemplateDAO.queryByCriterions(name, description,
        orderBy, order, start, pageSize);
    return oupts;
  }

  public void modifyOupt(OrgUnitPropTemplate oupt,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(oupt, "参数oupt不能为空！");
    Validate.notNull(oupt.getId(), "oupt.getId()不能为空！");

    OrgUnitPropTemplate old = orgUnitPropTemplateDAO.queryById(oupt.getId());
    orgUnitPropTemplateDAO.checkBeforeUpdate(oupt);
    BeanCopier.copyProperties(old, new String[] { "name", "innerName",
        "description" }, oupt);
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          orgUnitPropTemplateDAODW.checkBeforeUpdate(oupt);    
          OrgUnitPropTemplate oldDW = orgUnitPropTemplateDAODW.queryById(oupt.getId());
          oldDW.setName(oupt.getName());
          oldDW.setDescription(oupt.getDescription());
          oldDW.setInnerName(oupt.getInnerName());
        }
      }
  }

  public void modifyOuptItem(OuptItem ouptItem,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(ouptItem, "参数ouptItem不能为空！");
    Validate.notNull(ouptItem.getId(), "ouptItem.getId()不能为空！");

    OuptItem old = ouptItemDAO.queryById(ouptItem.getId());
    ouptItemDAO.checkBeforeUpdate(ouptItem);
    BeanCopier.copyProperties(old, new String[] { "name", "value", "valueType",
        "type", "nullable", "innerName", "enumValue" }, ouptItem);
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        ouptItemDAODW.checkBeforeUpdate(ouptItem);    
        ouptItemDAODW.updateDW(ouptItem);    
      }
    }
  }

  public void removeOupt(Serializable id,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");
    orgUnitPropTemplateDAO.deleteById(id);
    try{
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          orgUnitPropTemplateDAODW.deleteById(id);    
        }
      }
    }catch(BusinessException be){
      throw be;
    }
  }

  public void removeOuptItem(Serializable id,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");
    ouptItemDAO.deleteById(id);
    try{
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          ouptItemDAODW.deleteById(id);
        }
      }
    }catch(BusinessException be){
      throw be;
    }
  }

  public OuptDTO findOuptItems(Pagination pagination) throws BusinessException {

    OuptDTO ouptDTO = new OuptDTO();
    Serializable ouptId = (Serializable) pagination.getQueryCriterions().get(
        "ouptId");
    if (ouptId == null) {
      return ouptDTO;
    }

    String name = (String) pagination.getQueryCriterions().get("name");
    ValueTypeEnum valueType = (ValueTypeEnum) pagination.getQueryCriterions()
        .get("valueType");
    PropertyTypeEnum type = (PropertyTypeEnum) pagination.getQueryCriterions()
        .get("type");
    Boolean nullable = (Boolean) pagination.getQueryCriterions()
        .get("nullable");

    String orderBy = pagination.getOrderBy();
    OrderEnum order = pagination.getOrder();

    int count = ouptItemDAO.queryCountByCriterions(ouptId, name, valueType,
        type, nullable);
    pagination.setNrOfElements(count);

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List ouptItems = ouptItemDAO.queryByCriterions(ouptId, name, valueType,
        type, nullable, orderBy, order, start, pageSize);

    ouptDTO.setOupt(findOupt(ouptId));
    ouptDTO.setOuptItems(ouptItems);

    return ouptDTO;
  }

  // ================================================================

  public OrganizationUnit findOrgUnit(Serializable id) throws BusinessException {
    OrganizationUnit orgUnit = organizationUnitDAO.queryById(id);
    orgUnit.setAttributes(findAllAttributes(id));
    orgUnit.setParameters(findAllParameters(id));
    return orgUnit;
  }

  public List loadOrgUnitTree() {
    List orgUnits = organizationUnitDAO.queryAll(null);
    Iterator it = orgUnits.iterator();
    while (it.hasNext()) {
      OrganizationUnit orgUnit = (OrganizationUnit) it.next();
      initSubOrgUnits(orgUnit);
    }
    return orgUnits;
  }

  private void initSubOrgUnits(OrganizationUnit orgUnit) {
    Hibernate.initialize(orgUnit.getSubOrgUnits());
    int count = orgUnit.getSubOrgUnits().size();
    if (count == 0) {
      return;
    }
    Iterator it = orgUnit.getSubOrgUnits().iterator();
    while (it.hasNext()) {
      OrganizationUnit subOrgUnit = (OrganizationUnit) it.next();
      initSubOrgUnits(subOrgUnit);
    }
  }

  public Serializable createOrgUnit(OrganizationUnit orgUnit,SecurityInfo securityInfo)
      throws BusinessException {
    Validate.notNull(orgUnit, "参数orgUnit不能为空！");
    Serializable id = organizationUnitDAO.insert(orgUnit);
    Serializable id_=null;
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        organizationUnitDAODW.insertDW(orgUnit);    
      }
    }
    if (StringUtils.trimToNull(orgUnit.getOuptId()) != null) {
      List allProps = orgUnit.getOrgUnitProperties();
      Iterator it = allProps.iterator();
      while (it.hasNext()) {
        OrgUnitProperty oup = (OrgUnitProperty) it.next();
        oup.getOrganizationUnit().setId(orgUnit.getId());
        id_=orgUnitPropertyDAO.insert(oup);
        //判断是否存在单位版
        IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
        if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
          int isCentEdition =securityInfo.getIsOrgEdition();
          //判断是否为中心版
          if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
            OrgUnitProperty oup_ = oup;
            oup.getOrganizationUnit().setId(orgUnit.getId());
            orgUnitPropertyDAODW.insertDW(id_,oup_);
          }
        }
      }
    }    
    return id;
  }

  public void modifyOrgUnit(OrganizationUnit orgUnit,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(orgUnit, "参数orgUnit不能为空！");
    Serializable id=null;
    OrganizationUnit old = organizationUnitDAO.queryById(orgUnit.getId());
    orgUnitPropertyDAO.deleteByOrgUnitId(old.getId());

    BeanCopier.copyProperties(old, new String[] { "name", "description",
        "position", "ouptId", "type" }, orgUnit);
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        organizationUnitDAODW.updateDW(orgUnit);    
      }
    }
    if (StringUtils.trimToNull(orgUnit.getOuptId()) != null) {
      List allProps = orgUnit.getOrgUnitProperties();
      Iterator it = allProps.iterator();
      while (it.hasNext()) {
        OrgUnitProperty oup = (OrgUnitProperty) it.next();
        oup.getOrganizationUnit().setId(orgUnit.getId());
        id=orgUnitPropertyDAO.insert(oup);
        //判断是否存在单位版
        IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
        if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
          int isCentEdition =securityInfo.getIsOrgEdition();
          //判断是否为中心版
          if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
            OrgUnitProperty oup_ = oup;
            oup.getOrganizationUnit().setId(orgUnit.getId());
            orgUnitPropertyDAODW.insertDW(id,oup_);
          }
        }
      }
    }

  }

  public void removeOrgUnit(Serializable id,SecurityInfo securityInfo) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");
    organizationUnitDAO.deleteById(id);
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        organizationUnitDAODW.deleteById(id);    
      }
    }
  }

  public List findAllOupts() {
    Pagination p = new Pagination(0, 1000, 1, "oupt.id", OrderEnum.ASC,
        new HashMap(0));
    return findOupts(p);
  }

  public List findAllAttributes(Serializable orgUnitId) {
    Validate.notNull(orgUnitId, "参数orgUnitId不能为空！");

    List all = orgUnitPropertyDAO.queryByCriterions(orgUnitId,
        PropertyTypeEnum.ATTRIBUTE);
    all.addAll(getOups(ouptItemDAO.queryUnusedOuptItems(orgUnitId,
        PropertyTypeEnum.ATTRIBUTE)));
    return all;
  }

  public List findAllParameters(Serializable orgUnitId) {
    Validate.notNull(orgUnitId, "参数orgUnitId不能为空！");

    List all = orgUnitPropertyDAO.queryByCriterions(orgUnitId,
        PropertyTypeEnum.PARAMETER);
    all.addAll(getOups(ouptItemDAO.queryUnusedOuptItems(orgUnitId,
        PropertyTypeEnum.PARAMETER)));
    return all;
  }

  public List getAllAttributesFromOupt(Serializable ouptId) {
    Validate.notNull(ouptId, "参数ouptId不能为空！");

    List attrs = ouptItemDAO.queryByCriterions(ouptId, null, null,
        PropertyTypeEnum.ATTRIBUTE, null, "ouptItem.id", OrderEnum.ASC, 0, -1);
    return getOups(attrs);
  }

  public List getAllParametersFromOupt(Serializable ouptId) {
    Validate.notNull(ouptId, "参数ouptId不能为空！");

    List attrs = ouptItemDAO.queryByCriterions(ouptId, null, null,
        PropertyTypeEnum.PARAMETER, null, "ouptItem.id", OrderEnum.ASC, 0, -1);
    return getOups(attrs);
  }

  private List getOups(List attrs) {
    List attrs2 = new ArrayList();
    for (int i = 0; i < attrs.size(); i++) {
      OuptItem item = (OuptItem) attrs.get(i);
      attrs2.add(item.createOrgUnitProperty());
    }
    return attrs2;
  }

  public void assignDuRelationsToHafEmployee(List duRelations,
      Serializable userId) throws BusinessException {
    Validate.notNull(duRelations, "参数duRelations不能为空！");
    Validate.notNull(userId, "参数userId不能为空！");

    dataAccessControlBS.assignDuRelationsToUser(duRelations, userId);
  }

  public List findDuRelationsAvailable(Serializable userId) {
    List all = new ArrayList();
    all.addAll(dataAccessControlBS.findDuRelationsByUserId(userId));
    all.addAll(getDuRelations(dataAccessControlBS
        .findDataAccessesUnusedByUserId(userId)));
    Collections.sort(all);
    return all;
  }

  private List getDuRelations(List dataAccesses) {
    List list = new ArrayList();
    for (int i = 0; i < dataAccesses.size(); i++) {
      list.add(((DataAccess) dataAccesses.get(i)).createDuRelation());
    }
    return list;
  }

  public List findOperationsAvailableByUserId(Serializable userId) {

    return opControlBS.findOperationsUnusedByUserId(userId);
  }

  public List findOperationsByUserId(Serializable userId) {

    return opControlBS.findOperationsByUserId(userId);
  }

  public List findRolesAvailableByUserId(Serializable userId) {
    List all = hafRoleDAO.queryByCriterions(null, null, "hafRole.id",
        OrderEnum.ASC, 0, -1);
    List selected = hafRoleDAO.queryByUserId(userId);
    all.removeAll(selected);
    return all;
  }

  public List findRolesByUserId(Serializable userId) {

    return hafRoleDAO.queryByUserId(userId);
  }

  public List getAllOfficeList() {
    return securityDAO.getAllOfficeList();
  }

}
