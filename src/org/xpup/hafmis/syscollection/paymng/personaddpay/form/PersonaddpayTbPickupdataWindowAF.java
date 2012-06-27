package org.xpup.hafmis.syscollection.paymng.personaddpay.form;

import org.apache.struts.action.ActionForm;

public class PersonaddpayTbPickupdataWindowAF extends ActionForm {
  private static final long serialVersionUID = 0L;
  private String addpayDateSt = "";

  private String addpayDateEnd = "";
  
  private String paymentHeadId="";
  
  private String orgId="";
  
  private String noteNum="";
  
  private String noteNumB="";
  
  

  public String getAddpayDateEnd() {
    return addpayDateEnd;
  }

  public void setAddpayDateEnd(String addpayDateEnd) {
    this.addpayDateEnd = addpayDateEnd;
  }

  public String getAddpayDateSt() {
    return addpayDateSt;
  }

  public void setAddpayDateSt(String addpayDateSt) {
    this.addpayDateSt = addpayDateSt;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getPaymentHeadId() {
    return paymentHeadId;
  }

  public void setPaymentHeadId(String paymentHeadId) {
    this.paymentHeadId = paymentHeadId;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getNoteNumB() {
    return noteNumB;
  }

  public void setNoteNumB(String noteNumB) {
    this.noteNumB = noteNumB;
  }
}
