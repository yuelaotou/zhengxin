package org.xpup.hafmis.sysloan.dataready.bank.dto;

import java.math.BigDecimal;

public class BankDTO {
  private Integer id=null;
  /**
  * 银行名称
  */
 private BigDecimal bankName = new BigDecimal(0.0);
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
 private String loanBnakSt=null;
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

 
public BigDecimal getBankName() {
  return bankName;
}
public void setBankName(BigDecimal bankName) {
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
 public String getLoanBnakSt() {
   return loanBnakSt;
 }
 /**
  * 设置银行状态
  */
 public void setLoanBnakSt(String loanBnakSt) {
   this.loanBnakSt = loanBnakSt;
 }
public Integer getId() {
  return id;
}
public void setId(Integer id) {
  this.id = id;
}
}
