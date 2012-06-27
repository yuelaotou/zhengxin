package org.xpup.hafmis.syscollection.tranmng.tranout.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class TranImportTaAF extends ActionForm {
  private FormFile theFile = null;

  private String url = "";

  // 转出单位编号 转出单位名称 转入单位编号 转入单位名称 票据编号

  private String orgOutid;

  private String orgOutName;

  private String orgInid;

  private String orgInName;

  private String noteNum;

  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgInid() {
    return orgInid;
  }

  public void setOrgInid(String orgInid) {
    this.orgInid = orgInid;
  }

  public String getOrgInName() {
    return orgInName;
  }

  public void setOrgInName(String orgInName) {
    this.orgInName = orgInName;
  }

  public String getOrgOutid() {
    return orgOutid;
  }

  public void setOrgOutid(String orgOutid) {
    this.orgOutid = orgOutid;
  }

  public String getOrgOutName() {
    return orgOutName;
  }

  public void setOrgOutName(String orgOutName) {
    this.orgOutName = orgOutName;
  }

}
