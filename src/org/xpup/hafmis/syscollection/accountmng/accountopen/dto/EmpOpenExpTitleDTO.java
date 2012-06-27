package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

/**
 * 职工开户头信息导出模版
 * 
 * @author 孙亮 2007-7-11
 */
public class EmpOpenExpTitleDTO implements ExpDto {
  
  private String orgunitcode = null;


  private String orgunitname = null;
  public String getInfo(String info) {
    if (info.equals("orgunitcode")) {
      return this.orgunitcode;

    }
    if (info.equals("orgunitname")) {
      return this.orgunitname;

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



}
