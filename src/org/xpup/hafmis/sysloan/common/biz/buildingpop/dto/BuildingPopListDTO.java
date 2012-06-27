package org.xpup.hafmis.sysloan.common.biz.buildingpop.dto;

public class BuildingPopListDTO {
  private String floorId;
  
  private String developerId;
  
  private String developerName;
  
  private String floorName;

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

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }
}
