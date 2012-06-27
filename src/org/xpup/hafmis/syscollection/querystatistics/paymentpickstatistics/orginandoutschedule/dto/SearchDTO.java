package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto;

public class SearchDTO {
  private String officeCode;
  private String collectionBankId;
  private String orgId;
  private String orgName;
  private String character;
  private String deptInCharge;
  private String dateStart;
  private String dateEnd;
  private String region;
  public String getCharacter() {
    return character;
  }
  public void setCharacter(String character) {
    this.character = character;
  }
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getDateEnd() {
    return dateEnd;
  }
  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }
  public String getDateStart() {
    return dateStart;
  }
  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }
  public String getDeptInCharge() {
    return deptInCharge;
  }
  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
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
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
}
