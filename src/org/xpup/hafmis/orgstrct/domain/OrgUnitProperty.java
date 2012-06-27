package org.xpup.hafmis.orgstrct.domain;

import org.xpup.common.domain.DomainObject;

public class OrgUnitProperty extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * 是否已经保存
   */
  private boolean saved = true;

  /**
   * 属性或参数的取值
   */
  private String value = null;

  /**
   * 属性所属的组织单元
   */
  private OrganizationUnit organizationUnit = new OrganizationUnit();

  /**
   * 属性所对应的模板项
   */
  private OuptItem ouptItem = new OuptItem();

  public boolean isSaved() {
    return saved;
  }

  public void setSaved(boolean saved) {
    this.saved = saved;
  }

  public OuptItem getOuptItem() {
    return ouptItem;
  }

  public void setOuptItem(OuptItem ouptItem) {
    this.ouptItem = ouptItem;
  }

  public OrganizationUnit getOrganizationUnit() {
    return organizationUnit;
  }

  public void setOrganizationUnit(OrganizationUnit organizationUnit) {
    this.organizationUnit = organizationUnit;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
