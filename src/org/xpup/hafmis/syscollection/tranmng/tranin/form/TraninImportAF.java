package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * shiyan
 */
public class TraninImportAF extends ActionForm {
  /*
   * Generated Methods
   */

  /**
   * Method validate
   * 
   * @param mapping
   * @param request
   * @return ActionErrors
   */
  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  private FormFile theFile = null;

  private String url = "";

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Method reset
   * 
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
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