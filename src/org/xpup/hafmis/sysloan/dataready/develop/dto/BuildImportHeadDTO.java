package org.xpup.hafmis.sysloan.dataready.develop.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class BuildImportHeadDTO extends impDto{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String developerId = "";
  private String developerName = "";
  private String floorId = "";
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
