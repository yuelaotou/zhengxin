package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class OrRelation extends DomainObject {

  private static final long serialVersionUID = -4645765920848346421L;

  private Operation operation = new Operation();

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

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
