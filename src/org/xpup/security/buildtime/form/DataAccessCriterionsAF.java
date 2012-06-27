package org.xpup.security.buildtime.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class DataAccessCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = 4157755057287412425L;

  private String name = null;

  private String innerName = null;

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

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.name = null;
    this.innerName = null;
  }

}
