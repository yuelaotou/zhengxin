package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class ChgpersonImpTitleDTO extends impDto{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String orgunitcode;
  private String orgunitcodecontent;
  private String orgunitname;
  private String orgunitnamecontent;
  private String orgunitchgmonth;
  private String orgunitchgmonthcontent;
  
  public String getOrgunitchgmonth() {
    return orgunitchgmonth;
  }
  public void setOrgunitchgmonth(String orgunitchgmonth) {
    this.orgunitchgmonth = orgunitchgmonth;
  }
  public String getOrgunitchgmonthcontent() {
    return orgunitchgmonthcontent;
  }
  public void setOrgunitchgmonthcontent(String orgunitchgmonthcontent) {
    this.orgunitchgmonthcontent = orgunitchgmonthcontent;
  }
  public String getOrgunitcode() {
    return orgunitcode;
  }
  public void setOrgunitcode(String orgunitcode) {
    this.orgunitcode = orgunitcode;
  }
  public String getOrgunitcodecontent() {
    return orgunitcodecontent;
  }
  public void setOrgunitcodecontent(String orgunitcodecontent) {
    this.orgunitcodecontent = orgunitcodecontent;
  }
  public String getOrgunitname() {
    return orgunitname;
  }
  public void setOrgunitname(String orgunitname) {
    this.orgunitname = orgunitname;
  }
  public String getOrgunitnamecontent() {
    return orgunitnamecontent;
  }
  public void setOrgunitnamecontent(String orgunitnamecontent) {
    this.orgunitnamecontent = orgunitnamecontent;
  }

}
