package org.xpup.hafmis.sysloan.dataready.develop.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class BuildExportHeadDTO implements ExpDto{

  private String developerId = "";
  private String developerName = "";
  private String floorId = "";
  private String floorName = "";
  
  public String getInfo(String s) {
    if (s.equals("developerId"))
      return developerId;
    if (s.equals("developerName"))
      return developerName;
    if (s.equals("floorId"))
      return floorId;
    if (s.equals("floorName"))
      return floorName;

    else
      return null;
  }

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
