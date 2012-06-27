package org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class QueryCongeallogDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7174451406709378153L;

  private String contactId = "";
  private String officeName = "";
  private String bankId = "";
  private String orgId = "";
  private String orgName = "";
  private String empId = "";
  private String empName = "";
  private String cardNum = "";
  private BigDecimal money = new BigDecimal("0.00");
  private BigDecimal loanMoney = new BigDecimal("0.00");
  private BigDecimal loanTime = new BigDecimal("0.00");
  private String status = "";
  private String type = "";
  
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getContactId() {
    return contactId;
  }
  public void setContactId(String contactId) {
    this.contactId = contactId;
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
  public BigDecimal getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }
  public BigDecimal getLoanTime() {
    return loanTime;
  }
  public void setLoanTime(BigDecimal loanTime) {
    this.loanTime = loanTime;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
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
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
}
