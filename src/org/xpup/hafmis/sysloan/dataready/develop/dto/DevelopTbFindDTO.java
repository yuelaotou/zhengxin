package org.xpup.hafmis.sysloan.dataready.develop.dto;

/**
 * 封装开发商维护页查询条件的DTO
 * 
 * @author 付云峰
 */
public class DevelopTbFindDTO {

  /** 开发商编号 */
  private String developerId = "";

  /** 开发商名称 */
  private String developerName = "";

  /** 协议签订日期开始 */
  private String startAgreementStartDate = "";

  /** 协议签订日期结束 */
  private String endAgreementStartDate = "";

  /** 协议到期日期开始 */
  private String startAgreementEndDate = "";

  /** 协议到期日期结束 */
  private String endAgreementEndDate = "";

  /** 办事处 */
  private String office = "";

  /** 开发商状态 */
  private String developerSt = "";

  /** 联系人 */
  private String contactPrsnA = "";

  /** 组织机构代码 */
  private String code = "";

  /** 法人代表 */
  private String artfclprsn = "";

  /** 开户行 */
  private String paybank = "";

  /** 开户行账号 */
  private String paybankacc = "";

  private String developerPaybankA = "";// 开户行A

  private String developerPaybankB = "";// 开户行B
  
  private String buyhouseorgid; // 开发商名称，用来查询的

  private String floorName; // 楼盘名称

  private String floorNum; // 楼栋
  
  private String isSpecial;

  public String getIsSpecial() {
    return isSpecial;
  }

  public void setIsSpecial(String isSpecial) {
    this.isSpecial = isSpecial;
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

  public String getEndAgreementEndDate() {
    return endAgreementEndDate;
  }

  public void setEndAgreementEndDate(String endAgreementEndDate) {
    this.endAgreementEndDate = endAgreementEndDate;
  }

  public String getEndAgreementStartDate() {
    return endAgreementStartDate;
  }

  public void setEndAgreementStartDate(String endAgreementStartDate) {
    this.endAgreementStartDate = endAgreementStartDate;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getStartAgreementEndDate() {
    return startAgreementEndDate;
  }

  public void setStartAgreementEndDate(String startAgreementEndDate) {
    this.startAgreementEndDate = startAgreementEndDate;
  }

  public String getStartAgreementStartDate() {
    return startAgreementStartDate;
  }

  public void setStartAgreementStartDate(String startAgreementStartDate) {
    this.startAgreementStartDate = startAgreementStartDate;
  }

  public String getContactPrsnA() {
    return contactPrsnA;
  }

  public void setContactPrsnA(String contactPrsnA) {
    this.contactPrsnA = contactPrsnA;
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

  public String getPaybank() {
    return paybank;
  }

  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

  public String getPaybankacc() {
    return paybankacc;
  }

  public void setPaybankacc(String paybankacc) {
    this.paybankacc = paybankacc;
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

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
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
}
