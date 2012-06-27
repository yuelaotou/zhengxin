package org.xpup.hafmis.syscollection.paymng.personaddpay.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class PersonAddPayImportAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  /**
   * Method validate
   * 
   * @param mapping
   * @param request
   * @return ActionErrors
   */
  private FormFile theFile = null;

  private String orgId = "";

  private String orgName = "";

  private String noteNum = "";

  private String url = "";

  private String payKind = "";

  private String payWay = "";

  private String payment_orgname = "";

  public String getPayKind() {
    return payKind;
  }

  public void setPayKind(String payKind) {
    this.payKind = payKind;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }

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

  public String getPayment_orgname() {
    return payment_orgname;
  }

  public void setPayment_orgname(String payment_orgname) {
    this.payment_orgname = payment_orgname;
  }
}