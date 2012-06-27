package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrgaddpayHeadPrintDto implements Serializable{


  private static final long serialVersionUID = 7824999389240436586L;
  
  private String orgId="";
  private String orgName="";
  private String payMonth="";
  private String startPayMonth="";
  private String docNum="";
  private String personCounts="";
  private String pay = "";

  private String office="";
  private String collbankname="";
  private String collbankid;
  private String bankid="";
  private String bankname="";
  
  private  String  receivables_orgname=""; //收款单位名称
 
  private  String receivables_bank_acc="";  //收款单位归集银行账号
  
  private String receivables_bank_name="";  // 收款单位归集银行名称
 
  private String payment_orgname="";   //  付款单位名称
  
  private String payment_bank_acc="";  //  付款单位开户银行账号
  
  private String payment_bank_name=""; // 付款单位开户银行名称
  
  private String  payWay="";  // 缴存方式
  
  private String  payKind="";  // 缴存类别
  
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
  public String getPay() {
    return pay;
  }
  public void setPay(String pay) {
    this.pay = pay;
  }

  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
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

  public String getPersonCounts() {
    return personCounts;
  }
  public void setPersonCounts(String personCounts) {
    this.personCounts = personCounts;
  }
  public String getStartPayMonth() {
    return startPayMonth;
  }
  public void setStartPayMonth(String startPayMonth) {
    this.startPayMonth = startPayMonth;
  }
  public String getPayKind() {
    return payKind;
  }
  public void setPayKind(String payKind) {
    this.payKind = payKind;
  }
  public String getPayment_bank_acc() {
    return payment_bank_acc;
  }
  public void setPayment_bank_acc(String payment_bank_acc) {
    this.payment_bank_acc = payment_bank_acc;
  }
  public String getPayment_bank_name() {
    return payment_bank_name;
  }
  public void setPayment_bank_name(String payment_bank_name) {
    this.payment_bank_name = payment_bank_name;
  }
  public String getPayment_orgname() {
    return payment_orgname;
  }
  public void setPayment_orgname(String payment_orgname) {
    this.payment_orgname = payment_orgname;
  }
  public String getPayWay() {
    return payWay;
  }
  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }
  public String getReceivables_bank_acc() {
    return receivables_bank_acc;
  }
  public void setReceivables_bank_acc(String receivables_bank_acc) {
    this.receivables_bank_acc = receivables_bank_acc;
  }
  public String getReceivables_bank_name() {
    return receivables_bank_name;
  }
  public void setReceivables_bank_name(String receivables_bank_name) {
    this.receivables_bank_name = receivables_bank_name;
  }
  public String getReceivables_orgname() {
    return receivables_orgname;
  }
  public void setReceivables_orgname(String receivables_orgname) {
    this.receivables_orgname = receivables_orgname;
  }
 

}
