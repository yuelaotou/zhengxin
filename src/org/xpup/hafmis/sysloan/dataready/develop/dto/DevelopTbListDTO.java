package org.xpup.hafmis.sysloan.dataready.develop.dto;

/**
 * 封装列表内容的DTO
 * 
 * @author 付云峰
 */
public class DevelopTbListDTO {

  /** 主键 */
  private String id = "";

  /** 开发商编号 */
  private String developerId = "";

  /** 开发商名称 */
  private String developerName = "";

  /** 协议签订日期 */
  private String agreementStartDate = "";

  /** 协议到期日期结束 */
  private String agreementEndDate = "";

  /** 办事处 */
  private String office = "";

  /** 开发商状态 */
  private String developerSt = "";

  /** 联系人 */
  private String contactPrsnA = "";

  /** 联系电话 */
  private String contactTelA = "";

  /** 楼盘数 */
  private int sumFloor = 0;

  /** 楼栋数 */
  private int sumFloorNum = 0;

  private String code = "";// 组织机构代码

  private String floorName = "";// 楼盘名称

  private String developerPaybankA = "";// 开户行A

  private String developerPaybankB = "";// 开户行B

  private String artfclprsn = "";// 法人代表

  private String photoUrl = "";

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getAgreementEndDate() {
    return agreementEndDate;
  }

  public void setAgreementEndDate(String agreementEndDate) {
    this.agreementEndDate = agreementEndDate;
  }

  public String getAgreementStartDate() {
    return agreementStartDate;
  }

  public void setAgreementStartDate(String agreementStartDate) {
    this.agreementStartDate = agreementStartDate;
  }

  public String getContactPrsnA() {
    return contactPrsnA;
  }

  public void setContactPrsnA(String contactPrsnA) {
    this.contactPrsnA = contactPrsnA;
  }

  public String getContactTelA() {
    return contactTelA;
  }

  public void setContactTelA(String contactTelA) {
    this.contactTelA = contactTelA;
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

  public String getDeveloperSt() {
    return developerSt;
  }

  public void setDeveloperSt(String developerSt) {
    this.developerSt = developerSt;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public int getSumFloor() {
    return sumFloor;
  }

  public void setSumFloor(int sumFloor) {
    this.sumFloor = sumFloor;
  }

  public int getSumFloorNum() {
    return sumFloorNum;
  }

  public void setSumFloorNum(int sumFloorNum) {
    this.sumFloorNum = sumFloorNum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getArtfclprsn() {
    return artfclprsn;
  }

  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDeveloperPaybankA() {
    return developerPaybankA;
  }

  public void setDeveloperPaybankA(String developerPaybankA) {
    this.developerPaybankA = developerPaybankA;
  }

  public String getDeveloperPaybankB() {
    return developerPaybankB;
  }

  public void setDeveloperPaybankB(String developerPaybankB) {
    this.developerPaybankB = developerPaybankB;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }
}
