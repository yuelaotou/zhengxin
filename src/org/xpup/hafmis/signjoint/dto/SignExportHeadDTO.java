package org.xpup.hafmis.signjoint.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class SignExportHeadDTO implements ExpDto{

  //Orgid 单位编号，orgname 单位名称
  private String orgid="";
  private String orgname="";
  public String getInfo(String s) {
    if(s.equals("orgid"))
      return orgid;
    if(s.equals("orgname"))
      return orgname;
    else
      return null;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  
}
