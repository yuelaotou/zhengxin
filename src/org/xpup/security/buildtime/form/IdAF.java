package org.xpup.security.buildtime.form;

import org.apache.struts.action.ActionForm;

public class IdAF extends ActionForm {

  private static final long serialVersionUID = -3682003756214616260L;

  private String id = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
