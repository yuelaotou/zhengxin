package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;

import org.xpup.security.common.domain.Role;

public class HafRole extends Role {

  private static final long serialVersionUID = 3237984769505328006L;

  private String description = null;

  /**
   * 所属的组织单元
   */
  private OrganizationUnit organizationUnit = new OrganizationUnit();

  /**
   * 办事处ID
   */
  private Serializable officeId = null;

  /**
   * 部门ID
   */
  private Serializable departmentId = null;

  /**
   * 操作员ID
   */
  private Serializable operatorId = null;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OrganizationUnit getOrganizationUnit() {
    return organizationUnit;
  }

  public void setOrganizationUnit(OrganizationUnit organizationUnit) {
    this.organizationUnit = organizationUnit;
  }

  public Serializable getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Serializable departmentId) {
    this.departmentId = departmentId;
  }

  public Serializable getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Serializable officeId) {
    this.officeId = officeId;
  }

  public Serializable getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Serializable operatorId) {
    this.operatorId = operatorId;
  }

}
