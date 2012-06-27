package org.xpup.hafmis.sysloan.dataready.bank.dto;

import java.io.Serializable;

/**
 * 张列
 * @author Administrator
 *
 */
public class BankAFDTO implements Serializable {
  
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
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
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
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
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
  public String getLoanBankSt() {
    return loanBankSt;
  }
  public void setLoanBankSt(String loanBankSt) {
    this.loanBankSt = loanBankSt;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
