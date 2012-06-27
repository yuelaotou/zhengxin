package org.xpup.security.buildtime.form;

import org.apache.struts.action.ActionForm;

public abstract class CriterionsAF extends ActionForm {

  private int pageSize = 10;

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

}
