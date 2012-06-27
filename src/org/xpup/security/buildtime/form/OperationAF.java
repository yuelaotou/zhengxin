package org.xpup.security.buildtime.form;

import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.security.common.domain.Operation;

public class OperationAF extends DomainObjectAF {

  private static final long serialVersionUID = -3113171408361857942L;

  private Operation operation = new Operation();

  private List groups = new ArrayList();

  public List getGroups() {
    return groups;
  }

  public void setGroups(List groups) {
    this.groups = groups;
  }

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }
}
