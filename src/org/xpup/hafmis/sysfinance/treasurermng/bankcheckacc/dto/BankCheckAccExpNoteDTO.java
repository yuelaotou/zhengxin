package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class BankCheckAccExpNoteDTO implements ExpDto {
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
