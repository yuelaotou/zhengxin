package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class EmpOpenImpNoteDTO extends impDto{
  private String note;
  private String docmun;
  public String getDocmun() {
    return docmun;
  }
  public void setDocmun(String docmun) {
    this.docmun = docmun;
  }
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }
}
