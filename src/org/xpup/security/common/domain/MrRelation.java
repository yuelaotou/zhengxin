package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class MrRelation extends DomainObject {

  private static final long serialVersionUID = -1251098452994467465L;

  private MenuItem menuItem = new MenuItem();

  private Role role = new Role();

  /**
   * ÊÚÈ¨Êý
   */
  private int authNumber = 1;

  public int getAuthNumber() {
    return authNumber;
  }

  public void setAuthNumber(int authNumber) {
    this.authNumber = authNumber;
  }

  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
