package org.xpup.hafmis.syscollection.paymng.monthpay.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class MonthpayTbFindAF extends CriterionsAF {

  private static final long serialVersionUID = 157830469042818336L;

  private String id = "";

  private String name = "";
  private String status = "";
  //始
  private String inceptMonth = "";
  //至
  private String payMonth = "";
  //登记、入帐等
  private String payType = "";
  private String inceptPayMoney = "" ;
  private String payMoney = "";
  private String inceptSettlementDate = "" ;
  private String settlementDate = "";
  private String listCount="";
  private String settDate="";
  private String settDate1="";
  private String collBankId = "";
  private BigDecimal totalmoney = new BigDecimal(0);

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
    //始
    inceptMonth = "";
    //至
    payMonth = "";
    //登记、入帐等
    payType = "";
    inceptPayMoney = "" ;
    payMoney = "";
    inceptSettlementDate = "" ;
    settlementDate = "";
     collBankId = "";
  }

  public String getInceptMonth() {
    return inceptMonth;
  }

  public void setInceptMonth(String inceptMonth) {
    this.inceptMonth = inceptMonth;
  }

  public String getInceptPayMoney() {
    return inceptPayMoney;
  }

  public void setInceptPayMoney(String inceptPayMoney) {
    this.inceptPayMoney = inceptPayMoney;
  }

  public String getInceptSettlementDate() {
    return inceptSettlementDate;
  }

  public void setInceptSettlementDate(String inceptSettlementDate) {
    this.inceptSettlementDate = inceptSettlementDate;
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

  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public BigDecimal getTotalmoney() {
    return totalmoney;
  }

  public void setTotalmoney(BigDecimal totalmoney) {
    this.totalmoney = totalmoney;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
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
}