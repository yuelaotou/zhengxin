package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class MuRelation extends DomainObject {

  private static final long serialVersionUID = -2316771983182652505L;

  private User user = new User();

  private MenuItem menuItem = new MenuItem();

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
