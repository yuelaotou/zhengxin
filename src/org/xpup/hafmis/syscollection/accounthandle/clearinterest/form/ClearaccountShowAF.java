package org.xpup.hafmis.syscollection.accounthandle.clearinterest.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ClearaccountShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String officecode = "";

  private String bankcode = "";

  private String orgcode = "";

  private String orgname = "";

  private String oper = "";

  private List list;

  public String getBankcode() {
    return bankcode;
  }

  public void setBankcode(String bankcode) {
    this.bankcode = bankcode;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getOper() {
    return oper;
  }

  public void setOper(String oper) {
    this.oper = oper;
  }

  public String getOrgcode() {
    return orgcode;
  }

  public void setOrgcode(String orgcode) {
    this.orgcode = orgcode;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    this.officecode = "";
    this.bankcode = "";
    this.orgcode = "";
    this.orgname = "";
    this.oper = "";
    super.reset(arg0, arg1);
  }
}
