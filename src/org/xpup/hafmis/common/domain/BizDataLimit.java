package org.xpup.hafmis.common.domain;

import java.io.Serializable;

import org.xpup.hafmis.common.domain.enums.DataLevelEnum;

public class BizDataLimit {

  private DataLevelEnum dataLevel = null;

  private Serializable id = null;

  public DataLevelEnum getDataLevel() {
    return dataLevel;
  }

  public void setDataLevel(DataLevelEnum dataLevel) {
    this.dataLevel = dataLevel;
  }

  public Serializable getId() {
    return id;
  }

  public void setId(Serializable id) {
    this.id = id;
  }

  public BizDataLimit() {
  }

  public BizDataLimit(DataLevelEnum dataLevel, String id) {
    this.dataLevel = dataLevel;
    this.id = id;
  }
}
