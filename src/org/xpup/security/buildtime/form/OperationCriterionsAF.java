package org.xpup.security.buildtime.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class OperationCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = 1011507143341077977L;

  private String name = null;

  private String innerName = null;

  private String group = null;

  private String description = null;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String sysName) {
    this.innerName = sysName;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.name = null;
    this.innerName = null;
    this.description = null;
    this.group = null;
  }

}
