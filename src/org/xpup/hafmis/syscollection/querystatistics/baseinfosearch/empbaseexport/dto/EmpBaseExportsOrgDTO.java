package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class EmpBaseExportsOrgDTO implements ExpDto{
  private String orgId;

  private String orgName;

  public String getInfo(String info) {
    if (info.equals("orgId")) {
      return this.orgId;
    }
    if (info.equals("orgName")) {
      return this.orgName;
    }
    return null;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
