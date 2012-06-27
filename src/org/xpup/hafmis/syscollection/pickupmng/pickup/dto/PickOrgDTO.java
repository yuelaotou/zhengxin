package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class PickOrgDTO implements ExpDto{
  private String orgId;
  private String orgName;
  private String orgNoteNumber;
  private String orgunitcode = null;
  private String orgunitname = null;
//  if(s.equals("id"))
//    return id;
//  else
//    return null;
  public String getInfo(String s) {
    if(s.equals("orgId"))
      return orgId;
    if(s.equals("orgName"))
      return orgName;
    if(s.equals("orgNoteNumber"))
      return orgNoteNumber;
    if (s.equals("orgunitcode")) {
      return this.orgunitcode;

    }
    if (s.equals("orgunitname")) {
      return this.orgunitname;

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
  public String getOrgNoteNumber() {
    return orgNoteNumber;
  }
  public void setOrgNoteNumber(String orgNoteNumber) {
    this.orgNoteNumber = orgNoteNumber;
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
