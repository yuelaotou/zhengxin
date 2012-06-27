package org.xpup.hafmis.orgstrct.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OuptCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = -5929284821532769789L;

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

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.name = null;
    this.description = null;
  }

}
