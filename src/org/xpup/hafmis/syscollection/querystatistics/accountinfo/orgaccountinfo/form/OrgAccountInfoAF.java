package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OrgAccountInfoAF extends CriterionsAF {

  private static final long serialVersionUID = 157830469042818336L;

  private String orgid = "";

  private String orgname = "";
  
  private String opDate = "";
  
  private String inOpDate = "";
  
  private String mode = "";
  
  private String officecode = "";
  
  private String collBankId = "";

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getOpDate() {
    return opDate;
  }

  public void setOpDate(String opDate) {
    this.opDate = opDate;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    orgid = "";
    orgname = "";
    opDate = "";
    inOpDate = "";
    mode = "";
    officecode = "";
    collBankId = "";
  }

  public String getInOpDate() {
    return inOpDate;
  }

  public void setInOpDate(String inOpDate) {
    this.inOpDate = inOpDate;
  }

}