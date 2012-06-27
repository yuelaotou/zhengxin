package org.xpup.hafmis.sysloan.dataready.develop.dto;

public class HouseAddListDTO {
  /** pl006_1的主键 **/
  private String buildId;
  /** 房屋号 **/
  private String buildNum;
  /** 房屋地址 **/
  private String buildAdd;
  
  public String getBuildId() {
    return buildId;
  }
  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }
  public String getBuildAdd() {
    return buildAdd;
  }
  public void setBuildAdd(String buildAdd) {
    this.buildAdd = buildAdd;
  }
  public String getBuildNum() {
    return buildNum;
  }
  public void setBuildNum(String buildNum) {
    this.buildNum = buildNum;
  }
  
}
