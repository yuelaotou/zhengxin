package org.xpup.hafmis.orgstrct.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.xpup.common.domain.DomainObject;
import org.xpup.hafmis.orgstrct.domain.enums.OUTypeEnum;

public class OrganizationUnit extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * 组织单元名称
   */
  private String name = null;

  /**
   * 组织单元描述
   */
  private String description = null;

  /**
   * 组织类型
   */
  private int type = 1;

  /**
   * 位于同级机构中的位置
   */
  private int position = 0;

  /**
   * 所使用的组织单元属性模板
   */
  private String ouptId = null;

  private List attributes = new ArrayList();

  private List parameters = new ArrayList();

  /**
   * 组织单元的上级组织单元
   */
  private OrganizationUnit parent = null;

  /**
   * 组织单位的下级组织单元
   */
  private Set subOrgUnits = new LinkedHashSet();

  public List getAttributes() {
    return attributes;
  }

  public void setAttributes(List attributes) {
    this.attributes = attributes;
  }

  public List getParameters() {
    return parameters;
  }

  public void setParameters(List parameters) {
    this.parameters = parameters;
  }

  public List getOrgUnitProperties() {
    List all = new ArrayList();
    all.addAll(getParameters());
    all.addAll(getAttributes());
    return all;
  }

  public String getOuptId() {
    return ouptId;
  }

  public void setOuptId(String ouptId) {
    this.ouptId = ouptId;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public Set getSubOrgUnits() {
    return subOrgUnits;
  }

  public void setSubOrgUnits(Set subOrgUnits) {
    this.subOrgUnits = subOrgUnits;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OrganizationUnit getParent() {
    return parent;
  }

  public void setParent(OrganizationUnit parent) {
    this.parent = parent;
  }

  public boolean isRoot() {
    return getParent() != null ? false : true;
  }

  public boolean isLeaf() {
    int count = getSubOrgUnits().size();
    return count > 0 ? false : true;
  }

  public OUTypeEnum getType() {
    return OUTypeEnum.getEnum(type);
  }

  public void setType(OUTypeEnum type) {
    this.type = type.getValue();
  }

}
