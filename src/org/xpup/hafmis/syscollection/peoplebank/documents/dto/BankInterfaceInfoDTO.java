package org.xpup.hafmis.syscollection.peoplebank.documents.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了银行接口查询信息的DTO Goldsoft Project :
 * BankInterfaceInfoDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.2.14
 */
public class BankInterfaceInfoDTO {
  /** 识别码 */
  private String identifyCode="";

  /** 个人账号 */
  private String accounts="";

  /** 发生地点 */
  private String happenLocus="";

  /** 数据发生机构 */
  private String organization="";

  /** 职工姓名 */
  private String empName="";

  /** 身份证号 */
  private String cardNum="";

  /** 单位名称 */
  private String orgName="";

  /** 单位性质 */
  private String character="";

  /** 开户日期 YYYYMMDD */
  private String openDate="";

  /** 初缴年月 YYYYMM */
  private String payMonth="";

  /** 缴至年月 YYYYMM */
  private String payOverMonth="";

  /** 缴存状态 */
  private String payStatus="";

  /** 最近缴存日期 YYYYMMDD */
  private String lastPayMonth="";

  /** 缴额 */
  private String pay="";

  public String getAccounts() {
    return accounts;
  }

  public void setAccounts(String accounts) {
    this.accounts = accounts;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getIdentifyCode() {
    return identifyCode;
  }

  public void setIdentifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
  }

  public String getLastPayMonth() {
    return lastPayMonth;
  }

  public void setLastPayMonth(String lastPayMonth) {
    this.lastPayMonth = lastPayMonth;
  }

  public String getOpenDate() {
    return openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public String getPayOverMonth() {
    return payOverMonth;
  }

  public void setPayOverMonth(String payOverMonth) {
    this.payOverMonth = payOverMonth;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getHappenLocus() {
    return happenLocus;
  }

  public void setHappenLocus(String happenLocus) {
    this.happenLocus = happenLocus;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }
}
