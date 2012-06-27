package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.form;

import org.apache.struts.action.ActionForm;

public class EmpBaseExportsAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private String orgid = "";

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
}