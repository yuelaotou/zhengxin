package org.xpup.hafmis.sysloan.dataready.develop.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class BuildExportDTO  implements ExpDto {
  private String buildId = "";

  /** persistent field */
  private String floorId = "";

  /** nullable persistent field */
  private String buildAdd = "";

  /** nullable persistent field */
  private String buildNum = "";

  /** persistent field */
  private String build_s = "";

  /** nullable persistent field */
  private String fundStatus= "";
  
  /** nullable persistent field */
  private String reserved = "";

  public String getInfo(String s) {
    if (s.equals("buildId"))
      return buildId;
    if (s.equals("floorId"))
      return floorId;
    if (s.equals("buildAdd"))
      return buildAdd;
    if (s.equals("buildNum"))
      return buildNum;
    if (s.equals("build_s"))
      return build_s;
    if (s.equals("fundStatus"))
      return fundStatus;
    if (s.equals("reserved"))
      return reserved;
    else
      return null;
  }

  public String getBuild_s() {
    return build_s;
  }

  public void setBuild_s(String build_s) {
    this.build_s = build_s;
  }

  public String getBuildAdd() {
    return buildAdd;
  }

  public void setBuildAdd(String buildAdd) {
    this.buildAdd = buildAdd;
  }

  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public String getBuildNum() {
    return buildNum;
  }

  public void setBuildNum(String buildNum) {
    this.buildNum = buildNum;
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
