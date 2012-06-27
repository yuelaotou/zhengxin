package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.dto;

public class LoanapplyInfoDTO {

  private String contactid;

  private String borrowername;

  private String cardnum;

  private String huosetype;

  private String shousetolprice = "0";// 商品房总价

  private String shousearea = "0";// 商品房面积

  private String ehousetolprice = "0";// 二手房总价

  private String ehousearea = "0";// 二手房面积

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

  public String getClearDate() {
    return clearDate;
  }

  public void setClearDate(String clearDate) {
    this.clearDate = clearDate;
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

  public String getEhousearea() {
    return ehousearea;
  }

  public void setEhousearea(String ehousearea) {
    this.ehousearea = ehousearea;
  }

  public String getEhousetolprice() {
    return ehousetolprice;
  }

  public void setEhousetolprice(String ehousetolprice) {
    this.ehousetolprice = ehousetolprice;
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

  public String getShousearea() {
    return shousearea;
  }

  public void setShousearea(String shousearea) {
    this.shousearea = shousearea;
  }

  public String getShousetolprice() {
    return shousetolprice;
  }

  public void setShousetolprice(String shousetolprice) {
    this.shousetolprice = shousetolprice;
  }

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

  public String getLoanBalance() {
    return loanBalance;
  }

  public void setLoanBalance(String loanBalance) {
    this.loanBalance = loanBalance;
  }

}
