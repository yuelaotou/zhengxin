package org.xpup.hafmis.sysloan.loanapply.specialapply.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpecialapplyDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  // 借款人职工编号
  private String privilegeBorrowerId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件类型
  private String cardKind = "";

  // 证件号码
  private String cardNum = "";

  // 单位编号
  private String orgId = "";

  // 单位名称
  private String orgName = "";

  // 职工编号
  private String empId = "";

  private String stutas = "";

  private String operator = "";

  private String opTime = "";

  private String userIp = "";

  private String reserveaA = "";

  private String reserveaB = "";

  private String reserveaC = "";

  private String perBank = "";

  private String perBankAcc = "";
  
  private String headId = "";
  
  private String floorId = "";

  /** loanTimeLimit property */
  private String loanTimeLimit = "";

  /** loanMoney property */
  private BigDecimal loanMoney = new BigDecimal(0.00);

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

  public String getStutas() {
    return stutas;
  }

  public void setStutas(String stutas) {
    this.stutas = stutas;
  }

  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
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

  public String getPrivilegeBorrowerId() {
    return privilegeBorrowerId;
  }

  public void setPrivilegeBorrowerId(String privilegeBorrowerId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
  }

  public String getPerBank() {
    return perBank;
  }

  public void setPerBank(String perBank) {
    this.perBank = perBank;
  }

  public String getPerBankAcc() {
    return perBankAcc;
  }

  public void setPerBankAcc(String perBankAcc) {
    this.perBankAcc = perBankAcc;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }
}
