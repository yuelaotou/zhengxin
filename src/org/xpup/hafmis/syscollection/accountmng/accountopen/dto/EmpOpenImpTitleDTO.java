package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class EmpOpenImpTitleDTO extends impDto{

  private String orgunitcode;
  private String orgunitname;

  public String getOrgunitcode() {
    return orgunitcode;
  }
  public void setOrgunitcode(String orgunitcode) {
    this.orgunitcode = orgunitcode;
  }

  public String getOrgunitname() {
    return orgunitname;
  }
  public void setOrgunitname(String orgunitname) {
    this.orgunitname = orgunitname;
  }


}
