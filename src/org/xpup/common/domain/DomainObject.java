package org.xpup.common.domain;

import java.io.Serializable;

public abstract class DomainObject implements Serializable {
  public Serializable getId() {
    return id;
  }

  public void setId(Serializable id) {
    this.id = id;
  }

  public int hashCode() {
    return 17 + ((id == null) ? 0 : id.hashCode());
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof DomainObject)) {
      return false;
    }

    Serializable id = ((DomainObject) obj).id;
    if (id != null && id.equals(this.id)) {
      return true;
    }

    return super.equals(obj);
  }

  public boolean isWarned() {
    return warned;
  }

  public void setWarned(boolean warned) {
    this.warned = warned;
  }

  private Serializable id;

  private boolean warned = false;
}
