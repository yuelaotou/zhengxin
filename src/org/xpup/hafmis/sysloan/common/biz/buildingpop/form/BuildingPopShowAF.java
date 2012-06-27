package org.xpup.hafmis.sysloan.common.biz.buildingpop.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class BuildingPopShowAF extends ActionForm {
  
  private String developerId;
  
  private String developerName;
  
  private List list;

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  
}
