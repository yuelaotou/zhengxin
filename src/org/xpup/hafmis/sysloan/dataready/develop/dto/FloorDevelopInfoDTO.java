package org.xpup.hafmis.sysloan.dataready.develop.dto;

import java.math.BigDecimal;

/**
 * 封装楼盘中的开发商信息
 * 
 * @author fuyunfeng
 */
public class FloorDevelopInfoDTO {
  /**
   * 开发商编号
   */
  private String developerId = "";

  /**
   * 开发商名称
   */
  private String developerName = "";

  /**
   * 国有土地使用权证编号
   */
  private String landUseId = "";

  /**
   * 商品房销售许可证编号
   */
  private String salePermis = "";

  /**
   * 组织机构代码
   */
  private String code = "";

  /**
   * 办事处
   */
  private String office = "";

  /**
   * 协议签订日期
   */
  private String agreementStartDate = "";

  /**
   * 协议到期日期
   */
  private String agreementEndDate = "";

  /**
   * 开户银行A
   */
  private String developerPaybankA = "";

  /**
   * 开户帐号A
   */
  private String developerPaybankAAcc = "";

  /**
   * 开户银行B
   */
  private String developerPaybankB = "";

  /**
   * 开户帐号B
   */
  private String developerPaybankBAcc = "";
  /**
   * 开发商地址
   */
  private String developerAddr = "";

  private String fundStatus = "";

  private String artfclprsn = "";

  private BigDecimal registerFundNumber = new BigDecimal("0.00");

  public String getArtfclprsn() {
    return artfclprsn;
  }

  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }

  public BigDecimal getRegisterFundNumber() {
    return registerFundNumber;
  }

  public void setRegisterFundNumber(BigDecimal registerFundNumber) {
    this.registerFundNumber = registerFundNumber;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDeveloperAddr() {
    return developerAddr;
  }

  public void setDeveloperAddr(String developerAddr) {
    this.developerAddr = developerAddr;
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

  public String getDeveloperPaybankA() {
    return developerPaybankA;
  }

  public void setDeveloperPaybankA(String developerPaybankA) {
    this.developerPaybankA = developerPaybankA;
  }

  public String getDeveloperPaybankAAcc() {
    return developerPaybankAAcc;
  }

  public void setDeveloperPaybankAAcc(String developerPaybankAAcc) {
    this.developerPaybankAAcc = developerPaybankAAcc;
  }

  public String getLandUseId() {
    return landUseId;
  }

  public void setLandUseId(String landUseId) {
    this.landUseId = landUseId;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getSalePermis() {
    return salePermis;
  }

  public void setSalePermis(String salePermis) {
    this.salePermis = salePermis;
  }

  public String getFundStatus() {
    return fundStatus;
  }

  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }

  public String getDeveloperPaybankB() {
    return developerPaybankB;
  }

  public void setDeveloperPaybankB(String developerPaybankB) {
    this.developerPaybankB = developerPaybankB;
  }

  public String getDeveloperPaybankBAcc() {
    return developerPaybankBAcc;
  }

  public void setDeveloperPaybankBAcc(String developerPaybankBAcc) {
    this.developerPaybankBAcc = developerPaybankBAcc;
  }
}
