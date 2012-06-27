package org.xpup.security.common.domain;


public class DrRelation extends DaRelation {

  private static final long serialVersionUID = 4476295659412763713L;

  private Role role = new Role();

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
