package org.xpup.hafmis.sysloan.dataready.develop.dto;

import java.math.BigDecimal;

public class BuildDTO {
  /** persistent field */
  private String buildId = "";

  /** persistent field */
  private String floorId = "";

  /** nullable persistent field */
  private String buildAdd = "";

  /** nullable persistent field */
  private String buildNum = "";

  /** persistent field */
  private BigDecimal build_s = new BigDecimal("0.00");

  /** nullable persistent field */
  private String fundStatus= "";
  
  /** nullable persistent field */
  private String reserved = "";
  
  private String developerId = "";
  private String developerName = "";
  private String floorNum = "";
  private String floorName = "";

  public String getDeveloperId() {
    return developerId;
  }

  public void setDeveloperId(String developerId) {
    this.developerId = developerId;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public BigDecimal getBuild_s() {
    return build_s;
  }

  public void setBuild_s(BigDecimal build_s) {
    this.build_s = build_s;
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

  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getFundStatus() {
    return fundStatus;
  }

  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }

  public String getReserved() {
    return reserved;
  }

  public void setReserved(String reserved) {
    this.reserved = reserved;
  }
}
