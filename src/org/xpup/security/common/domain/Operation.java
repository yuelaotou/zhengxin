package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class Operation extends DomainObject {

  private static final long serialVersionUID = 2087578050151416324L;

  private String name = null;

  private String innerName = null;

  private String description = null;

  private String group = null;

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String sysName) {
    this.innerName = sysName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
