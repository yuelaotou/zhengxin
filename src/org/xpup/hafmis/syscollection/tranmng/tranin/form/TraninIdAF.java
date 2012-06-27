package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.util.List;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

/**
 * shiyan
 */
public class TraninIdAF extends IdAF {

  /**
   * 
   */
  private static final long serialVersionUID = 4314073321220405479L;

  private TranInTail tranInTail = new TranInTail();

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  private List list;

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public TranInTail getTranInTail() {
    return tranInTail;
  }

  public void setTranInTail(TranInTail tranInTail) {
    this.tranInTail = tranInTail;
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

}
