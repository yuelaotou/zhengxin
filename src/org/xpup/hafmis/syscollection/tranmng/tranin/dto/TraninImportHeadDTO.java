package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class TraninImportHeadDTO extends impDto {

  private static final long serialVersionUID = 0L;

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getInOrgName() {
    return inOrgName;
  }

  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

}
