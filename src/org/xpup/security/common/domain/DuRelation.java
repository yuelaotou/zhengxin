package org.xpup.security.common.domain;

public class DuRelation extends DaRelation {

  private static final long serialVersionUID = 4476295659412763713L;

  private User user = new User();

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
