package org.xpup.hafmis.orgstrct.domain;

import org.xpup.common.domain.DomainObject;

public class OrgUnitPropTemplate extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * 模板名称
   */
  private String name = null;

  /**
   * 内部名称
   */
  private String innerName = null;

  /**
   * 模板描述
   */
  private String description = null;

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String innerName) {
    this.innerName = innerName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
