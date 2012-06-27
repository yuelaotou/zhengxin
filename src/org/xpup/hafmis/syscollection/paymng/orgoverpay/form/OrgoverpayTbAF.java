package org.xpup.hafmis.syscollection.paymng.orgoverpay.form;

import java.util.HashMap;
import java.util.Map;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OrgoverpayTbAF extends CriterionsAF {

  private static final long serialVersionUID = 1L;
  private String orgId = "";
  private String unitName = "";
  private String bizStatus = "";
  private String payMonth = "";
  private String payMonth1 = "";
  private String payMoney = "";
  private String payType = "";
  private String settlementDate = "";
  private String listCount="";
  private String money = "";
  private String opTime = "";
  private String opTime1 = "";
  private Map payTypeMap=new HashMap();
  public String getListCount() {
    return listCount;
  }
  public void setListCount(String listCount) {
    this.listCount = listCount;
  }
  public String getMoney() {
    return money;
  }
  public void setMoney(String money) {
    this.money = money;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getPayMoney() {
    return payMoney;
  }
  public void setPayMoney(String payMoney) {
    this.payMoney = payMoney;
  }
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }
  public String getPayType() {
    return payType;
  }
  public void setPayType(String payType) {
    this.payType = payType;
  }
  public Map getPayTypeMap() {
    return payTypeMap;
  }
  public void setPayTypeMap(Map payTypeMap) {
    this.payTypeMap = payTypeMap;
  }
  public String getSettlementDate() {
    return settlementDate;
  }
  public void setSettlementDate(String settlementDate) {
    this.settlementDate = settlementDate;
  }
 
  public String getBizStatus() {
    return bizStatus;
  }
  public void setBizStatus(String bizStatus) {
    this.bizStatus = bizStatus;
  }
  public String getUnitName() {
    return unitName;
  }
  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }
  public String getOpTime1() {
    return opTime1;
  }
  public void setOpTime1(String opTime1) {
    this.opTime1 = opTime1;
  }
  public String getPayMonth1() {
    return payMonth1;
  }
  public void setPayMonth1(String payMonth1) {
    this.payMonth1 = payMonth1;
  }

}