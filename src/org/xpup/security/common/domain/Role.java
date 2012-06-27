package org.xpup.security.common.domain;

import java.util.Set;

import org.xpup.common.domain.DomainObject;

public class Role extends DomainObject {

  private static final long serialVersionUID = 3529997884883690747L;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getUserCount() {
    return userCount;
  }

  public Set getUsers() {
    return users;
  }

  private String name = null;

  private Set users = null;

  private int userCount = 0;
}
