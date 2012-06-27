/*单位编号、单位名称、调整年月、职工编号、职工姓名、证件号码、调整前工资基数、调整后工资基数（空）、*/
package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;


public class ChgslarybaseHeadImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String orgId;

  private String orgName;

  private String chgMonth;
  
  private String payMode;
  
  private String payMode1;

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
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

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }

  public String getPayMode1() {
    return payMode1;
  }

  public void setPayMode1(String payMode1) {
    this.payMode1 = payMode1;
  }


}
