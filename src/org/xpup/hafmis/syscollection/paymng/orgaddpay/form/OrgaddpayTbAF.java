package org.xpup.hafmis.syscollection.paymng.orgaddpay.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OrgaddpayTbAF extends CriterionsAF {

  private static final long serialVersionUID = 157830469042818336L;

  private String id = "";
  private String name = "";
  private String status = "";
  private String payMonth = "";
  private String payMoney = "";
  private String payType = "";
  private String settlementDate = "";
  private String settlementDate1 = "";
  private String compare = "";
  private String listCount="";
  private String money = "";
  private String settDate="";
  private String settDate1="";
  private String commitStatus="";
  private String collBankId = "";
  private Map payTypeMap=new HashMap();


  public Map getPayTypeMap() {
    return payTypeMap;
  }

  public void setPayTypeMap(Map payTypeMap) {
    this.payTypeMap = payTypeMap;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    id = "";
    name = "";
    status = "";
    payMonth = "";
    payType = "";
    payMoney = "";
    settlementDate = "";
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSettlementDate() {
    return settlementDate;
  }

  public void setSettlementDate(String settlementDate) {
    this.settlementDate = settlementDate;
  }

  public String getCompare() {
    return compare;
  }

  public void setCompare(String compare) {
    this.compare = compare;
  }

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

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getCommitStatus() {
    return commitStatus;
  }

  public void setCommitStatus(String commitStatus) {
    this.commitStatus = commitStatus;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getSettDate1() {
    return settDate1;
  }

  public void setSettDate1(String settDate1) {
    this.settDate1 = settDate1;
  }

  public String getSettlementDate1() {
    return settlementDate1;
  }

  public void setSettlementDate1(String settlementDate1) {
    this.settlementDate1 = settlementDate1;
  }
}