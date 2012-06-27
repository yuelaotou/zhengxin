package org.xpup.hafmis.syscollection.pickupmng.pickup.form;

import java.math.BigDecimal;

public class NameAF {
  
  private String organizatinUnitName ="";
  private String payBank = "";
  private String payBankNum = "";
  private String centercollBankName = "";
  private String orgName = "";
  private BigDecimal money = new BigDecimal(0);
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  public String getOrganizatinUnitName() {
    return organizatinUnitName;
  }
  public void setOrganizatinUnitName(String organizatinUnitName) {
    this.organizatinUnitName = organizatinUnitName;
  }
  public String getCentercollBankName() {
    return centercollBankName;
  }
  public void setCentercollBankName(String centercollBankName) {
    this.centercollBankName = centercollBankName;
  }
  public String getPayBank() {
    return payBank;
  }
  public void setPayBank(String payBank) {
    this.payBank = payBank;
  }
  public String getPayBankNum() {
    return payBankNum;
  }
  public void setPayBankNum(String payBankNum) {
    this.payBankNum = payBankNum;
  }
 

}
