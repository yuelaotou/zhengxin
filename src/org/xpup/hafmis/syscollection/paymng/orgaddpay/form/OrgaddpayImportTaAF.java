
package org.xpup.hafmis.syscollection.paymng.orgaddpay.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class OrgaddpayImportTaAF extends ActionForm {
  /*
   * Generated Methods
   */

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /** 
   * Method validate
   * @param mapping
   * @param request
   * @return ActionErrors
   */
  private FormFile theFile = null;
  private String url="";
  private String orgid;
  private String noteNum;
  private String payMonth;
  private String startPayMonth;
  private String payKind;
  private String payWay;
  private String payment_orgname;
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

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
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
  public ActionErrors validate(ActionMapping mapping,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /** 
   * Method reset
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }

  public String getStartPayMonth() {
    return startPayMonth;
  }

  public void setStartPayMonth(String startPayMonth) {
    this.startPayMonth = startPayMonth;
  }

  public String getPayment_orgname() {
    return payment_orgname;
  }

  public void setPayment_orgname(String payment_orgname) {
    this.payment_orgname = payment_orgname;
  }
}