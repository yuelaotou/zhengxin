package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class OuRelation extends DomainObject {

  private static final long serialVersionUID = -2609499878836138774L;

  private User user = new User();

  private Operation operation = new Operation();

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

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
