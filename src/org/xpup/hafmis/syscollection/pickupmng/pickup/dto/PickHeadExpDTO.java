package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class PickHeadExpDTO implements ExpDto{
  private String orgunitcode = null;
  private String orgunitname = null;
  public String getInfo(String info) {
    if (info.equals("orgunitcode")) {
      return this.orgunitcode;

    }
    if (info.equals("orgunitname")) {
      return this.orgunitname;

    }
    return null;
  }
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
