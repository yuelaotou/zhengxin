package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.dto;

public class LoanapplyrealInfoDTO {

  private String contactid;

  private String borrowername;

  private String cardnum;

  private String huosetype;

  private String housetolprice;// 房总价

  private String housearea;// 房面积

  private String loanmoney;

  private String loanBalance;

  private String loanlimit;

  private String monthrate;

  private String paymood;

  private String loanBank; // 放款银行

  private String contractSt; // 合同状态

  private String agreementDate; // 合同签订日期

  private String isSignAgreement; // 是否签订公积金协议

  private String clearDate;

  public String getAgreementDate() {
    return agreementDate;
  }

  public void setAgreementDate(String agreementDate) {
    this.agreementDate = agreementDate;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getIsSignAgreement() {
    return isSignAgreement;
  }

  public void setIsSignAgreement(String isSignAgreement) {
    this.isSignAgreement = isSignAgreement;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public String getBorrowername() {
    return borrowername;
  }

  public void setBorrowername(String borrowername) {
    this.borrowername = borrowername;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getContactid() {
    return contactid;
  }

  public void setContactid(String contactid) {
    this.contactid = contactid;
  }

  public String getHuosetype() {
    return huosetype;
  }

  public void setHuosetype(String huosetype) {
    this.huosetype = huosetype;
  }

  public String getLoanlimit() {
    return loanlimit;
  }

  public void setLoanlimit(String loanlimit) {
    this.loanlimit = loanlimit;
  }

  public String getLoanmoney() {
    return loanmoney;
  }

  public void setLoanmoney(String loanmoney) {
    this.loanmoney = loanmoney;
  }

  public String getMonthrate() {
    return monthrate;
  }

  public void setMonthrate(String monthrate) {
    this.monthrate = monthrate;
  }

  public String getPaymood() {
    return paymood;
  }

  public void setPaymood(String paymood) {
    this.paymood = paymood;
  }

  public String getHousearea() {
    return housearea;
  }

  public void setHousearea(String housearea) {
    this.housearea = housearea;
  }

  public String getHousetolprice() {
    return housetolprice;
  }

  public void setHousetolprice(String housetolprice) {
    this.housetolprice = housetolprice;
  }

  public String getLoanBalance() {
    return loanBalance;
  }

  public void setLoanBalance(String loanBalance) {
    this.loanBalance = loanBalance;
  }

  public String getClearDate() {
    return clearDate;
  }

  public void setClearDate(String clearDate) {
    this.clearDate = clearDate;
  }

}
