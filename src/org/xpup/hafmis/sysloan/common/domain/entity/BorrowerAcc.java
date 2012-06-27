package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BorrowerAcc implements Serializable {

  /** identifier field */
  private String contractId;

  /** nullable persistent field */
  private String loanKouAcc;

  /** nullable persistent field */
  private BigDecimal loanMoney = new BigDecimal(0.00);

  /** nullable persistent field */
  private String loanTimeLimit;

  /** nullable persistent field */
  private String loanRateType;

  /** nullable persistent field */
  private String loanMode;

  /** nullable persistent field */
  private BigDecimal loanBankId = new BigDecimal(0.00);;

  /** nullable persistent field */
  private String contractSt;

  /** nullable persistent field */
  private String isContractWrite;

  /** nullable persistent field */
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);;

  /** nullable persistent field */
  private String overplusLimite;

  /** nullable persistent field */
  private String loanStartDate;

  /** nullable persistent field */
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);;

  /** nullable persistent field */
  private BigDecimal loanRepayDay = new BigDecimal(0.00);;

  /** nullable persistent field */
  private BigDecimal curIntegral = new BigDecimal(0.00);;

  /** nullable persistent field */
  private BigDecimal preIntegral = new BigDecimal(0.00);;

  /** nullable persistent field */
  private BigDecimal noBackMoney = new BigDecimal(0.00);;

  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;

  private String reasonA;

  private String reasonB;

  private BigDecimal bailBalance = new BigDecimal(0.00);

  private String oldContractSt;

  private String manualAuto;

  private String passReasonA;

  private String passReasonB;

  private String redatePerson;

  private String chkAgainPerson;

  private String vipChkAgainPerson;

  private String lastChkPerson;
  
  private String markA="";
  
  private String markB="";
  
  private String finCheckMan="";
  
  private String finPrintMan="";

  public String getChkAgainPerson() {
    return chkAgainPerson;
  }

  public void setChkAgainPerson(String chkAgainPerson) {
    this.chkAgainPerson = chkAgainPerson;
  }

  public String getLastChkPerson() {
    return lastChkPerson;
  }

  public void setLastChkPerson(String lastChkPerson) {
    this.lastChkPerson = lastChkPerson;
  }

  public String getRedatePerson() {
    return redatePerson;
  }

  public void setRedatePerson(String redatePerson) {
    this.redatePerson = redatePerson;
  }

  public String getVipChkAgainPerson() {
    return vipChkAgainPerson;
  }

  public void setVipChkAgainPerson(String vipChkAgainPerson) {
    this.vipChkAgainPerson = vipChkAgainPerson;
  }

  /** full constructor */
  public BorrowerAcc(String contractId, String loanKouAcc,
      BigDecimal loanMoney, String loanTimeLimit, String loanRateType,
      String loanMode, BigDecimal loanBankId, String contractSt,
      String isContractWrite, BigDecimal overplusLoanMoney,
      String overplusLimite, String loanStartDate, BigDecimal ovaerLoanRepay,
      BigDecimal loanRepayDay, BigDecimal curIntegral, BigDecimal preIntegral,
      BigDecimal noBackMoney, String reserveaA, String reserveaB,
      String reserveaC, String reasonA, String reasonB, BigDecimal bailBalance,
      String oldContractSt, String manualAuto, String passReasonA,
      String passReasonB) {
    this.contractId = contractId;
    this.loanKouAcc = loanKouAcc;
    this.loanMoney = loanMoney;
    this.loanTimeLimit = loanTimeLimit;
    this.loanRateType = loanRateType;
    this.loanMode = loanMode;
    this.loanBankId = loanBankId;
    this.contractSt = contractSt;
    this.isContractWrite = isContractWrite;
    this.overplusLoanMoney = overplusLoanMoney;
    this.overplusLimite = overplusLimite;
    this.loanStartDate = loanStartDate;
    this.ovaerLoanRepay = ovaerLoanRepay;
    this.loanRepayDay = loanRepayDay;
    this.curIntegral = curIntegral;
    this.preIntegral = preIntegral;
    this.noBackMoney = noBackMoney;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.reasonA = reasonA;
    this.reasonB = reasonB;
    this.bailBalance = bailBalance;
    this.oldContractSt = oldContractSt;
    this.manualAuto = manualAuto;
    this.passReasonA = passReasonA;
    this.passReasonB = passReasonA;
  }

  /** default constructor */
  public BorrowerAcc() {
  }

  /** minimal constructor */
  public BorrowerAcc(String contractId) {
    this.contractId = contractId;
  }

  public String getContractId() {
    return this.contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanKouAcc() {
    return this.loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getLoanMoney() {
    return this.loanMoney;
  }

  public String getReasonA() {
    return reasonA;
  }

  public void setReasonA(String reasonA) {
    this.reasonA = reasonA;
  }

  public String getReasonB() {
    return reasonB;
  }

  public void setReasonB(String reasonB) {
    this.reasonB = reasonB;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return this.loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getLoanRateType() {
    return this.loanRateType;
  }

  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }

  public String getLoanMode() {
    return this.loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getLoanBankId() {
    return this.loanBankId;
  }

  public void setLoanBankId(BigDecimal loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getContractSt() {
    return this.contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getIsContractWrite() {
    return this.isContractWrite;
  }

  public void setIsContractWrite(String isContractWrite) {
    this.isContractWrite = isContractWrite;
  }

  public BigDecimal getOverplusLoanMoney() {
    return this.overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getOverplusLimite() {
    return this.overplusLimite;
  }

  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }

  public String getLoanStartDate() {
    return this.loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public BigDecimal getOvaerLoanRepay() {
    return this.ovaerLoanRepay;
  }

  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }

  public BigDecimal getLoanRepayDay() {
    return this.loanRepayDay;
  }

  public void setLoanRepayDay(BigDecimal loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public BigDecimal getCurIntegral() {
    return this.curIntegral;
  }

  public void setCurIntegral(BigDecimal curIntegral) {
    this.curIntegral = curIntegral;
  }

  public BigDecimal getPreIntegral() {
    return this.preIntegral;
  }

  public void setPreIntegral(BigDecimal preIntegral) {
    this.preIntegral = preIntegral;
  }

  public BigDecimal getNoBackMoney() {
    return this.noBackMoney;
  }

  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
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

  public String getOldContractSt() {
    return oldContractSt;
  }

  public void setOldContractSt(String oldContractSt) {
    this.oldContractSt = oldContractSt;
  }

  public String getManualAuto() {
    return manualAuto;
  }

  public void setManualAuto(String manualAuto) {
    this.manualAuto = manualAuto;
  }

  public String getPassReasonA() {
    return passReasonA;
  }

  public void setPassReasonA(String passReasonA) {
    this.passReasonA = passReasonA;
  }

  public String getPassReasonB() {
    return passReasonB;
  }

  public void setPassReasonB(String passReasonB) {
    this.passReasonB = passReasonB;
  }

  public String toString() {
    return new ToStringBuilder(this).append("contractId", getContractId())
        .toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof BorrowerAcc))
      return false;
    BorrowerAcc castOther = (BorrowerAcc) other;
    return new EqualsBuilder().append(this.getContractId(),
        castOther.getContractId()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getContractId()).toHashCode();
  }

  public BigDecimal getBailBalance() {
    return bailBalance;
  }

  public void setBailBalance(BigDecimal bailBalance) {
    this.bailBalance = bailBalance;
  }

  public String getMarkA() {
    return markA;
  }

  public void setMarkA(String markA) {
    this.markA = markA;
  }

  public String getMarkB() {
    return markB;
  }

  public void setMarkB(String markB) {
    this.markB = markB;
  }

  public String getFinCheckMan() {
    return finCheckMan;
  }

  public void setFinCheckMan(String finCheckMan) {
    this.finCheckMan = finCheckMan;
  }

  public String getFinPrintMan() {
    return finPrintMan;
  }

  public void setFinPrintMan(String finPrintMan) {
    this.finPrintMan = finPrintMan;
  }

}
