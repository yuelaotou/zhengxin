package org.xpup.hafmis.sysloan.loanapply.loanapply.dto;

/**
 * hanl 商品房信息
 * 
 * @author Administrator
 */
public class BuyHouserDTO {
  private String developerName;

  private String developerTel;

  private String buildingArea;// 建筑面积

  private String housePrice;// 房价

  private String landuseid;// 产权证

  private String agreementstartdate;// 签合同日期

  public String getAgreementstartdate() {
    return agreementstartdate;
  }

  public void setAgreementstartdate(String agreementstartdate) {
    this.agreementstartdate = agreementstartdate;
  }

  public String getBuildingArea() {
    return buildingArea;
  }

  public void setBuildingArea(String buildingArea) {
    this.buildingArea = buildingArea;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getDeveloperTel() {
    return developerTel;
  }

  public void setDeveloperTel(String developerTel) {
    this.developerTel = developerTel;
  }

  public String getHousePrice() {
    return housePrice;
  }

  public void setHousePrice(String housePrice) {
    this.housePrice = housePrice;
  }

  public String getLanduseid() {
    return landuseid;
  }

  public void setLanduseid(String landuseid) {
    this.landuseid = landuseid;
  }

}
