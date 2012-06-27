package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class TraninExportHeadDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  public String getInfo(String s) {
    if (s.equals("inOrgId"))
      return inOrgId;
    if (s.equals("inOrgName"))
      return inOrgName;
    if (s.equals("noteNum"))
      return noteNum;

    else
      return null;
  }

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
