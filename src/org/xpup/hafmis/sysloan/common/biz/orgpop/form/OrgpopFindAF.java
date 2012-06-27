package org.xpup.hafmis.sysloan.common.biz.orgpop.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OrgpopFindAF extends CriterionsAF {

  private static final long serialVersionUID = 157830469042818336L;

  private String id = "";

  private String name = "";
  
  private String oldId = "";

  public String getOldId() {
    return oldId;
  }

  public void setOldId(String oldId) {
    this.oldId = oldId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    id = "";
    name = "";
    oldId = "";
  }
}