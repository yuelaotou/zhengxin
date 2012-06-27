package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class EmpaddpayHeadImportDTO extends impDto{

  
  private static final long serialVersionUID = 1L;

  private String orgId=""; 

  private String orgName="";

  private String noteNum="";

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
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