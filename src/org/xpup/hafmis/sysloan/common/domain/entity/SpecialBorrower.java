package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SpecialBorrower implements Serializable {

  /** identifier field */
  private Integer privilegeBorrowerId;

  /** persistent field */
  private String borrowerName;

  /** persistent field */
  private String cardKind;

  /** persistent field */
  private String cardNum;

  /** nullable persistent field */
  private BigDecimal orgId;

  /** nullable persistent field */
  private String orgName;

  /** persistent field */
  private String loanTimeLimit;

  /** persistent field */
  private BigDecimal loanMoney;

  /** persistent field */
  private String status;

  /** persistent field */
  private String operator;

  /** persistent field */
  private Date opTime;

  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;

  private String perBank;

  private String perBankAcc;

  private BigDecimal empId = new BigDecimal(0.00);
  
  private String headId;
  
  private String floorId;
  
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

  /** full constructor */
  public SpecialBorrower(Integer privilegeBorrowerId, String borrowerName,
      String cardKind, String cardNum, BigDecimal orgId, String orgName,
      String loanTimeLimit, BigDecimal loanMoney, String status,
      String operator, Date opTime, String reserveaA, String reserveaB,
      String reserveaC, BigDecimal empId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
    this.borrowerName = borrowerName;
    this.cardKind = cardKind;
    this.cardNum = cardNum;
    this.orgId = orgId;
    this.orgName = orgName;
    this.loanTimeLimit = loanTimeLimit;
    this.loanMoney = loanMoney;
    this.status = status;
    this.operator = operator;
    this.opTime = opTime;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.empId = empId;
  }

  /** default constructor */
  public SpecialBorrower() {
  }

  /** minimal constructor */
  public SpecialBorrower(Integer privilegeBorrowerId, String borrowerName,
      String cardKind, String cardNum, String loanTimeLimit,
      BigDecimal loanMoney, String status, String operator, Date opTime) {
    this.privilegeBorrowerId = privilegeBorrowerId;
    this.borrowerName = borrowerName;
    this.cardKind = cardKind;
    this.cardNum = cardNum;
    this.loanTimeLimit = loanTimeLimit;
    this.loanMoney = loanMoney;
    this.status = status;
    this.operator = operator;
    this.opTime = opTime;
  }

  public Integer getPrivilegeBorrowerId() {
    return this.privilegeBorrowerId;
  }

  public void setPrivilegeBorrowerId(Integer privilegeBorrowerId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
  }

  public String getBorrowerName() {
    return this.borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return this.cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return this.cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BigDecimal getOrgId() {
    return this.orgId;
  }

  public void setOrgId(BigDecimal orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return this.orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getLoanTimeLimit() {
    return this.loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public BigDecimal getLoanMoney() {
    return this.loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOperator() {
    return this.operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Date getOpTime() {
    return this.opTime;
  }

  public void setOpTime(Date opTime) {
    this.opTime = opTime;
  }

  public String getReserveaA() {
    return this.reserveaA;
  }

  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }

  public String getReserveaB() {
    return this.reserveaB;
  }

  public void setReserveaB(String reserveaB) {
    this.reserveaB = reserveaB;
  }

  public String getReserveaC() {
    return this.reserveaC;
  }

  public void setReserveaC(String reserveaC) {
    this.reserveaC = reserveaC;
  }

  public BigDecimal getEmpId() {
    return empId;
  }

  public void setEmpId(BigDecimal empId) {
    this.empId = empId;
  }

  public String toString() {
    return new ToStringBuilder(this).append("privilegeBorrowerId",
        getPrivilegeBorrowerId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof SpecialBorrower))
      return false;
    SpecialBorrower castOther = (SpecialBorrower) other;
    return new EqualsBuilder().append(this.getPrivilegeBorrowerId(),
        castOther.getPrivilegeBorrowerId()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getPrivilegeBorrowerId()).toHashCode();
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

}
