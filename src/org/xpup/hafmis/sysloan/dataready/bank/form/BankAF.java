package org.xpup.hafmis.sysloan.dataready.bank.form;

import org.apache.struts.action.ActionForm;

public class BankAF extends ActionForm{
  private static final long serialVersionUID = 1L;
  /**
   * ID
   */
  private Integer id=null;
  /**
   * 银行名称
   */
  private String bankName=null;
  /**
   * 所在中心办事处
   */
  private String office=null;
  /**
   * 中心委托贷款账号
   */
  private String loanAcc=null;
  /**
   * 中心利息账号
   */
  private String interestAcc=null;
  /**
   * 银行行长
   */
  private String bankPrisident=null;
  /**
   * 行长电话
   */
  private String bankPrisidentTel=null;
  /**
   * 联系人
   */
  private String contactPrsn=null;
  /**
   * 联系人电话
   */
  private String contactTel=null;
  /**
   * 联系人职务
   */
  private String business=null;
  /**
   * 银行状态
   */
  private String loanBankSt=null;
  /**
   * 分发状态
   */
  private String type=null; 
  
  private String outAccount="";
  
  private String inAccount="";
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  /**
   * 获取银行行长
   */
  public String getBankPrisident() {
    return bankPrisident;
  }
  /**
   * 设置银行行长
   */
  public void setBankPrisident(String bankPrisident) {
    this.bankPrisident = bankPrisident;
  }
  /**
   * 获取中心委托贷款账号
   */
  public String getLoanAcc() {
    return loanAcc;
  }
  /**
   * 设置中心委托贷款账号
   */
  public void setLoanAcc(String loanAcc) {
    this.loanAcc = loanAcc;
  }
  /**
   * 设置所在中心办事处
   */
  public String getOffice() {
    return office;
  }
  /**
   * 获取所在中心办事处
   */
  public void setOffice(String office) {
    this.office = office;
  }

  /**
   * 获取银行名称
   */
  public String getBankName() {
    return bankName;
  }

  /**
   * 设置银行名称
   */
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
  
  /**
   * 获取中心利息账号
   */
  public String getInterestAcc() {
    return interestAcc;
  }
  /**
   * 设置中心利息账号
   */
  public void setInterestAcc(String interestAcc) {
    this.interestAcc = interestAcc;
  }
  /**
   * 获取行长电话
   */
  public String getBankPrisidentTel() {
    return bankPrisidentTel;
  }
  /**
   * 设置行长电话
   */
  public void setBankPrisidentTel(String bankPrisidentTel) {
    this.bankPrisidentTel = bankPrisidentTel;
  }
  /**
   *获取联系人
   */
  public String getContactPrsn() {
    return contactPrsn;
  }
  /**
   * 设置联系人
   */
  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }
  /**
   * 获取联系人电话
   * @return
   */
  public String getContactTel() {
    return contactTel;
  }
  /**
   * 设置联系人电话
   * @param contactTel
   */
  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }
  /**
   * 获取联系人职务
   */
  public String getBusiness() {
    return business;
  }
  /**
   * 设置联系人职务
   */
  public void setBusiness(String business) {
    this.business = business;
  }
  /**
   * 获取银行状态
   */
  public String getLoanBankSt() {
    return loanBankSt;
  }
  /**
   * 设置银行状态
   */
  public void setLoanBankSt(String loanBnakSt) {
    this.loanBankSt = loanBnakSt;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getInAccount() {
    return inAccount;
  }
  public void setInAccount(String inAccount) {
    this.inAccount = inAccount;
  }
  public String getOutAccount() {
    return outAccount;
  }
  public void setOutAccount(String outAccount) {
    this.outAccount = outAccount;
  }
}
