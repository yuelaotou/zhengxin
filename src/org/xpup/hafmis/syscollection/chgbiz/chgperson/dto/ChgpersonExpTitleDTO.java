package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

/**
 * 人员变更头信息导出模版
 * 
 * @author 王玲 2007-7-10
 */
public class ChgpersonExpTitleDTO implements ExpDto {
  
  private String orgunitcode = null;

  private String orgunitchgmonth = null;

  private String orgunitname = null;
  
  private String orgunitcodecontent = null;

  private String orgunitchgmonthcontent = null;

  private String orgunitnamecontent = null;

  public String getInfo(String info) {
    if (info.equals("orgunitcode")) {
      return this.orgunitcode;

    }
    if (info.equals("orgunitname")) {
      return this.orgunitname;

    }
    if (info.equals("orgunitchgmonth")) {
      return this.orgunitchgmonth;

    }
    if (info.equals("orgunitcodecontent")) {
      return this.orgunitcodecontent;

    }
    if (info.equals("orgunitnamecontent")) {
      return this.orgunitnamecontent;

    }
    if (info.equals("orgunitchgmonthcontent")) {
      return this.orgunitchgmonthcontent;

    }
    return null;

  }

  /**
   * @return 返回 orgunitcode。
   */
  public String getOrgunitcode() {
    return orgunitcode;
  }

  /**
   * @param orgunitcode 要设置的 orgunitcode。
   */
  public void setOrgunitcode(String orgunitcode) {
    this.orgunitcode = orgunitcode;
  }

  /**
   * @return 返回 orgunitcodecontent。
   */
  public String getOrgunitchgmonth() {
    return orgunitchgmonth;
  }

  /**
   * @param orgunitcodecontent 要设置的 orgunitcodecontent。
   */
  public void setOrgunitchgmonth(String orgunitchgmonth) {
    this.orgunitchgmonth = orgunitchgmonth;
  }

  /**
   * @return 返回 orgunitname。
   */
  public String getOrgunitname() {
    return orgunitname;
  }

  /**
   * @param orgunitname 要设置的 orgunitname。
   */
  public void setOrgunitname(String orgunitname) {
    this.orgunitname = orgunitname;
  }

  public String getOrgunitchgmonthcontent() {
    return orgunitchgmonthcontent;
  }

  public void setOrgunitchgmonthcontent(String orgunitchgmonthcontent) {
    this.orgunitchgmonthcontent = orgunitchgmonthcontent;
  }

  public String getOrgunitcodecontent() {
    return orgunitcodecontent;
  }

  public void setOrgunitcodecontent(String orgunitcodecontent) {
    this.orgunitcodecontent = orgunitcodecontent;
  }

  public String getOrgunitnamecontent() {
    return orgunitnamecontent;
  }

  public void setOrgunitnamecontent(String orgunitnamecontent) {
    this.orgunitnamecontent = orgunitnamecontent;
  }

}
