package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgpersonExpNoteDTO implements ExpDto {
/**
 * 人员变更导出说明DTO
 */
  private String note="";

  public String getInfo(String info) {
    if (info.equals("note")) {
      return this.note;

    }
    return null;

  }
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
