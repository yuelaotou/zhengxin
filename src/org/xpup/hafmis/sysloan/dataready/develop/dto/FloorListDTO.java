package org.xpup.hafmis.sysloan.dataready.develop.dto;

/**
 * 显示楼盘列表的DTO
 * 
 * @author fuyunfeng
 */
public class FloorListDTO {

  /**
   * PL006表主键
   */
  private String floorId = "";

  /**
   * 准许销售楼盘
   */
  private String floorName = "";
  
  private String floorAddress = "";

  /**
   * 楼盘号
   */
  private String floorNum = "";

  /**
   * 所属地区
   */
  private String region = "";

  /**
   * 项目投资总额
   */
  private String itemTotleAmnt = "";

  /**
   * 项目完工程度
   */
  private String itemFinishDegree = "";

  /**
   * 住宅价格
   */
  private String housePrice = "";

  /**
   * 建筑面积
   */
  private String buildingArea = "";

  /**
   * 状态
   */
  private String floorSt = "";
  
  private String fundStatus = "";
  // 楼盘标识
  private String floorFlag = "";
  // 地址标识
  private String addressFlag = "";

  public String getAddressFlag() {
    return addressFlag;
  }

  public void setAddressFlag(String addressFlag) {
    this.addressFlag = addressFlag;
  }

  public String getFloorFlag() {
    return floorFlag;
  }

  public void setFloorFlag(String floorFlag) {
    this.floorFlag = floorFlag;
  }

  public String getFundStatus() {
    return fundStatus;
  }

  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }

  public String getBuildingArea() {
    return buildingArea;
  }

  public void setBuildingArea(String buildingArea) {
    this.buildingArea = buildingArea;
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

  public String getFloorSt() {
    return floorSt;
  }

  public void setFloorSt(String floorSt) {
    this.floorSt = floorSt;
  }

  public String getHousePrice() {
    return housePrice;
  }

  public void setHousePrice(String housePrice) {
    this.housePrice = housePrice;
  }

  public String getItemFinishDegree() {
    return itemFinishDegree;
  }

  public void setItemFinishDegree(String itemFinishDegree) {
    this.itemFinishDegree = itemFinishDegree;
  }

  public String getItemTotleAmnt() {
    return itemTotleAmnt;
  }

  public void setItemTotleAmnt(String itemTotleAmnt) {
    this.itemTotleAmnt = itemTotleAmnt;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getFloorAddress() {
    return floorAddress;
  }

  public void setFloorAddress(String floorAddress) {
    this.floorAddress = floorAddress;
  }
}
