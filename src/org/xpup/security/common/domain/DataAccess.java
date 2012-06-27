package org.xpup.security.common.domain;

import org.xpup.common.domain.DomainObject;

public class DataAccess extends DomainObject {

  private static final long serialVersionUID = 2051625763265347650L;

  private String name = null;

  private String innerName = null;

  private String queryLevelEnum = null;

  private String queryValueOrder = null;

  private String queryDefauldLevel = null;

  private String operationLevelEnum = null;

  private String operationValueOder = null;

  private String operationDefaultLevel = null;

  public String getOperationValueOder() {
    return operationValueOder;
  }

  public void setOperationValueOder(String operationValueOder) {
    this.operationValueOder = operationValueOder;
  }

  public String getQueryValueOrder() {
    return queryValueOrder;
  }

  public void setQueryValueOrder(String queryValueOrder) {
    this.queryValueOrder = queryValueOrder;
  }

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String innerName) {
    this.innerName = innerName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOperationDefaultLevel() {
    return operationDefaultLevel;
  }

  public void setOperationDefaultLevel(String operationDefaultLevel) {
    this.operationDefaultLevel = operationDefaultLevel;
  }

  public String getOperationLevelEnum() {
    return operationLevelEnum;
  }

  public void setOperationLevelEnum(String operationLevelEnum) {
    this.operationLevelEnum = operationLevelEnum;
  }

  public String getQueryDefauldLevel() {
    return queryDefauldLevel;
  }

  public void setQueryDefauldLevel(String queryDefauldLevel) {
    this.queryDefauldLevel = queryDefauldLevel;
  }

  public String getQueryLevelEnum() {
    return queryLevelEnum;
  }

  public void setQueryLevelEnum(String queryLevelEnum) {
    this.queryLevelEnum = queryLevelEnum;
  }

  public DuRelation createDuRelation() {
    DuRelation r = new DuRelation();
    r.setDataAccess(this);
    r.setQueryLevel(queryDefauldLevel);
    r.setOperationLevel(operationDefaultLevel);
    r.setSaved(false);
    return r;
  }

  public DrRelation createDrRelation() {
    DrRelation r = new DrRelation();
    r.setDataAccess(this);
    r.setQueryLevel(queryDefauldLevel);
    r.setOperationLevel(operationDefaultLevel);
    r.setSaved(false);
    return r;
  }

}
