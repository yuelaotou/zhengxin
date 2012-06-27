package org.xpup.security.buildtime.form;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.security.common.domain.DataAccess;

public class DataAccessAF extends DomainObjectAF {

  private static final long serialVersionUID = 3254238424691665691L;

  private DataAccess dataAccess = new DataAccess();

  public DataAccess getDataAccess() {
    return dataAccess;
  }

  public void setDataAccess(DataAccess dataAccess) {
    this.dataAccess = dataAccess;
  }

}
