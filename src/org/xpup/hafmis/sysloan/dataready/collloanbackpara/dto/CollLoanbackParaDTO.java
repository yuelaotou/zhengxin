package org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto;

import java.math.BigDecimal;

public class CollLoanbackParaDTO {
 private String office="";//办事处
 private String pickMoneyType="";//1、提取金额 
 private BigDecimal balance=new BigDecimal(0.00);//1、A.留足余额___ 元
 private String monthMoney="";//1、B.留足月还本息___ 月
 private String monthPayMoney="";//1、 C.留足月缴存额___ 月
 private String isDeduct="";//2、可扣公积金余额不足时是否扣款
// private String isOverPay="";//2、是否可挂账
 private String isPreOnly="";//3、只扣往年余额
 private String isPickLessThanPay="";//4、月提取额不超过月缴存额
 private String isOtherDeduct="";//5、辅助借款人是否可以扣款
public BigDecimal getBalance() {
  return balance;
}
public void setBalance(BigDecimal balance) {
  this.balance = balance;
}
public String getIsDeduct() {
  return isDeduct;
}
public void setIsDeduct(String isDeduct) {
  this.isDeduct = isDeduct;
}
public String getIsOtherDeduct() {
  return isOtherDeduct;
}
public void setIsOtherDeduct(String isOtherDeduct) {
  this.isOtherDeduct = isOtherDeduct;
}
//public String getIsOverPay() {
//  return isOverPay;
//}
//public void setIsOverPay(String isOverPay) {
//  this.isOverPay = isOverPay;
//}
public String getIsPreOnly() {
  return isPreOnly;
}
public void setIsPreOnly(String isPreOnly) {
  this.isPreOnly = isPreOnly;
}
public String getOffice() {
  return office;
}
public void setOffice(String office) {
  this.office = office;
}
public String getPickMoneyType() {
  return pickMoneyType;
}
public void setPickMoneyType(String pickMoneyType) {
  this.pickMoneyType = pickMoneyType;
}
public String getIsPickLessThanPay() {
  return isPickLessThanPay;
}
public void setIsPickLessThanPay(String isPickLessThanPay) {
  this.isPickLessThanPay = isPickLessThanPay;
}
public String getMonthMoney() {
  return monthMoney;
}
public void setMonthMoney(String monthMoney) {
  this.monthMoney = monthMoney;
}
public String getMonthPayMoney() {
  return monthPayMoney;
}
public void setMonthPayMoney(String monthPayMoney) {
  this.monthPayMoney = monthPayMoney;
}
}
