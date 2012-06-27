package org.xpup.hafmis.common.form;

import org.apache.struts.validator.ValidatorForm;

public abstract class DomainObjectAF extends ValidatorForm {

  public static final String CREATE = "CREATE";

  public static final String MODIFY = "MODIFY";

  private String activity = CREATE;

  private boolean createAgain = false;

  public boolean isCreateAgain() {
    return createAgain;
  }

  public void setCreateAgain(boolean createAgain) {
    this.createAgain = createAgain;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

}
