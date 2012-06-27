package org.xpup.hafmis.syscollection.paymng.orgoverpay.form;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OrgoverpayAF extends CriterionsAF {



private static final long serialVersionUID = 1L;
private String orgId="";
private String unitName="";
private String noteNum="";
private String banlance="";
private String amount="";
private String reason="";
private String overpayMonth="";
private String listCount="";
private String sign="";
private String id="";

private String office="";
private String collbankname="";
private String collbankid;
private String bankid="";
private String bankname="";

private BigDecimal orgBalance=new BigDecimal(0.00);
private String maker="";
private String bizDate="";
private String checker="";



private String orgName1="";
private String orgBank1="";
private String orgBankNum1="";
private String orgName2="";
private String orgBank2="";
private String orgBankNum2="";
private String creNum="";

private String type="";
private Map payTypeMap=new HashMap();
public String getBankid() {
  return bankid;
}
public void setBankid(String bankid) {
  this.bankid = bankid;
}
public String getBankname() {
  return bankname;
}
public void setBankname(String bankname) {
  this.bankname = bankname;
}
public String getCollbankid() {
  return collbankid;
}
public void setCollbankid(String collbankid) {
  this.collbankid = collbankid;
}
public String getCollbankname() {
  return collbankname;
}
public void setCollbankname(String collbankname) {
  this.collbankname = collbankname;
}
public String getOffice() {
  return office;
}
public void setOffice(String office) {
  this.office = office;
}
public String getId() {
  return id;
}
public void setId(String id) {
  this.id = id;
}
public String getSign() {
  return sign;
}
public void setSign(String sign) {
  this.sign = sign;
}
public String getListCount() {
  return listCount;
}
public void setListCount(String listCount) {
  this.listCount = listCount;
}
public static long getSerialVersionUID() {
  return serialVersionUID;
}
public String getOverpayMonth() {
  return overpayMonth;
}
public void setOverpayMonth(String overpayMonth) {
  this.overpayMonth = overpayMonth;
}
public String getAmount() {
  return amount;
}
public void setAmount(String amount) {
  this.amount = amount;
}

public String getBanlance() {
  return banlance;
}
public void setBanlance(String banlance) {
  this.banlance = banlance;
}
public String getNoteNum() {
  return noteNum;
}
public void setNoteNum(String noteNum) {
  this.noteNum = noteNum;
}
public String getOrgId() {
  return orgId;
}
public void setOrgId(String orgId) {
  this.orgId = orgId;
}
public String getReason() {
  return reason;
}
public void setReason(String reason) {
  this.reason = reason;
}
public String getUnitName() {
  return unitName;
}
public void setUnitName(String unitName) {
  this.unitName = unitName;
}
public void reset(ActionMapping arg0, HttpServletRequest arg1) {
  // TODO Auto-generated method stub
  this.orgId="";
  this.unitName="";
  this.noteNum="";
  this.banlance="";
  this.amount="";
  this.reason="";
  this.overpayMonth="";
  this.listCount="";
  this.sign="";
  this.id="";
}
public BigDecimal getOrgBalance() {
  return orgBalance;
}
public void setOrgBalance(BigDecimal orgBalance) {
  this.orgBalance = orgBalance;
}
public String getMaker() {
  return maker;
}
public void setMaker(String maker) {
  this.maker = maker;
}
public String getBizDate() {
  return bizDate;
}
public void setBizDate(String bizDate) {
  this.bizDate = bizDate;
}
public String getChecker() {
  return checker;
}
public void setChecker(String checker) {
  this.checker = checker;
}
public String getOrgBank1() {
  return orgBank1;
}
public void setOrgBank1(String orgBank1) {
  this.orgBank1 = orgBank1;
}
public String getOrgBank2() {
  return orgBank2;
}
public void setOrgBank2(String orgBank2) {
  this.orgBank2 = orgBank2;
}
public String getOrgBankNum1() {
  return orgBankNum1;
}
public void setOrgBankNum1(String orgBankNum1) {
  this.orgBankNum1 = orgBankNum1;
}
public String getOrgBankNum2() {
  return orgBankNum2;
}
public void setOrgBankNum2(String orgBankNum2) {
  this.orgBankNum2 = orgBankNum2;
}
public String getOrgName1() {
  return orgName1;
}
public void setOrgName1(String orgName1) {
  this.orgName1 = orgName1;
}
public String getOrgName2() {
  return orgName2;
}
public void setOrgName2(String orgName2) {
  this.orgName2 = orgName2;
}
public String getType() {
  return type;
}
public void setType(String type) {
  this.type = type;
}
public Map getPayTypeMap() {
  return payTypeMap;
}
public void setPayTypeMap(Map payTypeMap) {
  this.payTypeMap = payTypeMap;
}
public String getCreNum() {
  return creNum;
}
public void setCreNum(String creNum) {
  this.creNum = creNum;
}
}