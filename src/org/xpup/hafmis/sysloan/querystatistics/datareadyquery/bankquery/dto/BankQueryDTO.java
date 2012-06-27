package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto;

public class BankQueryDTO {
  /**
   * id主键
   */
  private String id = "";

  /**
   * 贷款银行序号
   */
  private String loanBankId = "";

  /**
   * 委托贷款账号
   */
  private String loanAcc = "";

  /**
   * 利息账号
   */
  private String interestAcc = "";

  /**
   * 银行行长
   */
  private String bankPrisident = "";

  /**
   * 行长电话
   */
  private String bankPrisidentTel = "";

  /**
   * 联系人
   */
  private String contactPrsn = "";

  /**
   * 联系人电话
   */
  private String contactTel = "";

  /**
   * 联系人职务
   */
  private String business = "";

  /**
   * 办事处
   */
  private String office = "";

  /**
   * 银行状态
   */
  private String loanBnakSt = "";

  public String getBankPrisident() {
    return bankPrisident;
  }

  public void setBankPrisident(String bankPrisident) {
    this.bankPrisident = bankPrisident;
  }

  public String getBankPrisidentTel() {
    return bankPrisidentTel;
  }

  public void setBankPrisidentTel(String bankPrisidentTel) {
    this.bankPrisidentTel = bankPrisidentTel;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getContactPrsn() {
    return contactPrsn;
  }

  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }

  public String getContactTel() {
    return contactTel;
  }

  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }

  public String getInterestAcc() {
    return interestAcc;
  }

  public void setInterestAcc(String interestAcc) {
    this.interestAcc = interestAcc;
  }

  public String getLoanAcc() {
    return loanAcc;
  }

  public void setLoanAcc(String loanAcc) {
    this.loanAcc = loanAcc;
  }

  public String getLoanBnakSt() {
    return loanBnakSt;
  }

  public void setLoanBnakSt(String loanBnakSt) {
    this.loanBnakSt = loanBnakSt;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
}
