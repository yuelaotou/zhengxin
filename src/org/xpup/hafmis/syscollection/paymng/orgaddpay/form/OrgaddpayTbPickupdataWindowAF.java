package org.xpup.hafmis.syscollection.paymng.orgaddpay.form;

import org.apache.struts.action.ActionForm;

public class OrgaddpayTbPickupdataWindowAF extends ActionForm {
  private static final long serialVersionUID = 0L;

  private String addpayDateSt = "";// 补缴开始年月

  private String addpayDateEnd = "";// 补缴结束年月

  private String orgId = "";// 单位编号

  private String noteNum = "";// 之前票据编号

  private String noteNumB = "";// 之后填入的票据编号

  private String orgName = "";// 单位名称

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

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
