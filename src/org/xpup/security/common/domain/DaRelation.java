package org.xpup.security.common.domain;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.xpup.common.domain.DomainObject;

public abstract class DaRelation extends DomainObject implements Comparable {

  private DataAccess dataAccess = new DataAccess();

  private String queryLevel = null;

  private String operationLevel = null;

  private boolean saved = true;

  public DataAccess getDataAccess() {
    return dataAccess;
  }

  public void setDataAccess(DataAccess dataAccess) {
    this.dataAccess = dataAccess;
  }

  public String getOperationLevel() {
    return operationLevel;
  }

  public void setOperationLevel(String operationLevel) {
    this.operationLevel = operationLevel;
  }

  public String getQueryLevel() {
    return queryLevel;
  }

  public void setQueryLevel(String queryLevel) {
    this.queryLevel = queryLevel;
  }

  public boolean isSaved() {
    return saved;
  }

  public void setSaved(boolean saved) {
    this.saved = saved;
  }

  public int compareTo(Object obj) {
    DaRelation da = (DaRelation) obj;
    return new CompareToBuilder().append(this.dataAccess.getName(),
        da.dataAccess.getName()).toComparison();
  }

}
