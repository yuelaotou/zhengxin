package org.xpup.hafmis.syscollection.querystatistics.collbyfund.form;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CollByFundAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  List list = new ArrayList();

  List printList = new ArrayList();

  List bybankList = new ArrayList();

  private String officeCode;

  private String collBankId;

  private String contractId;

  private String empName;

  private String empId;

  private String orgName;

  private String orgId;

  private String cardNum;

  private String begDate;

  private String endDate;

  private String batchNum;

  private String date;

  private BigDecimal money = new BigDecimal("0.00");

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getBegDate() {
    return begDate;
  }

  public void setBegDate(String begDate) {
    this.begDate = begDate;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
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

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public List getBybankList() {
    return bybankList;
  }

  public void setBybankList(List bybankList) {
    this.bybankList = bybankList;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}
