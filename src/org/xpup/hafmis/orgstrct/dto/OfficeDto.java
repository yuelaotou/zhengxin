package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;

public class OfficeDto implements Serializable{

  private static final long serialVersionUID = 5743046093074567834L;

  private String officeCode="";
  private String officeName="";
  
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }
  
}
