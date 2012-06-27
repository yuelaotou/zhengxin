package org.xpup.hafmis.orgstrct.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class HafRoleCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = 1190674168059570824L;

  private String name = null;

  private String description = null;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    super.reset(arg0, arg1);
    this.name = null;
    this.description = null;
  }

}
