package org.xpup.security.buildtime.form;

import org.apache.struts.validator.ValidatorForm;
import org.xpup.security.common.domain.MenuItem;

public class MenuItemAF extends ValidatorForm {

  private static final long serialVersionUID = 6612173059478757853L;

  public static final String CREATE = "CREATE";

  public static final String MODIFY = "MODIFY";

  private MenuItem menuItem = new MenuItem();

  private String parentId = null;

  private String activity = CREATE;

  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

}
