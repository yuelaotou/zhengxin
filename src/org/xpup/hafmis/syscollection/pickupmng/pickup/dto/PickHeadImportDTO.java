package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;
import org.xpup.common.util.imp.domn.interfaces.impDto;
public class PickHeadImportDTO extends impDto{
  private String orgNoteNumber;
  private String orgId;
  private String orgName;
  private String orgunitcode = null;
  private String orgunitname = null;
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
}
