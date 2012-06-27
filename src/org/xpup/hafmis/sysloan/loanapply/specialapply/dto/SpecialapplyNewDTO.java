package org.xpup.hafmis.sysloan.loanapply.specialapply.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpecialapplyNewDTO implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /** cardKind property */
  private String cardKind = "";

  /** orgId property */
  private Integer orgId = new Integer(0);

  /** borrowerName property */
  private String borrowerName = "";

  /** loanTimeLimit property */
  private String loanTimeLimit = "";

  /** loanMoney property */
  private BigDecimal loanMoney = new BigDecimal(0.00);

  /** privilegeBorrowerId property */
  private String privilegeBorrowerId = "";

  /** cardNum property */
  private String cardNum = "";

  /** orgName property */
  private String orgName = "";
  
  private String stutas = "";
  
  private String operator = "";
  
  private String opTime = "";
  
  private String userIp = "";
  
  private String reserveaA = "";
  
  private String reserveaB = "";
  
  private String reserveaC = "";
  
  private String empId = "";
  
  
  public String getUserIp() {
    return userIp;
  }
  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public BigDecimal getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }
  public Integer getOrgId() {
    return orgId;
  }
  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getPrivilegeBorrowerId() {
    return privilegeBorrowerId;
  }
  public void setPrivilegeBorrowerId(String privilegeBorrowerId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
  }
  public String getStutas() {
    return stutas;
  }
  public void setStutas(String stutas) {
    this.stutas = stutas;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getReserveaA() {
    return reserveaA;
  }
  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }
  public String getReserveaB() {
    return reserveaB;
  }
  public void setReserveaB(String reserveaB) {
    this.reserveaB = reserveaB;
  }
  public String getReserveaC() {
    return reserveaC;
  }
  public void setReserveaC(String reserveaC) {
    this.reserveaC = reserveaC;
  }
}
