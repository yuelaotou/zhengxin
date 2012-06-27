package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.dto;

import java.math.BigDecimal;

/**
 * @author 王野 2007-09-27
 */
public class LoanVIPCheckDTO {

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardNum = "";// 证件号码

  private String totalPrice = "";// 房屋总价

  private BigDecimal housePrice = new BigDecimal(0.00);

  private BigDecimal loanMoney_ = new BigDecimal(0.00);

  private String loanMoney = "";// 借款金额

  private String loanTimeLimit = "";// 借款期限

  private String loanBankName = "";// 放款银行

  private String orgName = "";// 单位名称

  private String houseArea = "";// 建筑面积（M2）

  private String houseAddr = "";// 房屋地址

  private String houseType = "";// 购房类型

  private String contractSt = "";// 合同状态

  private String assistantborrowerName = "";// 配偶姓名

  private String type = "";// 是否为特殊借款人 １为特殊借款人，０不为特殊借款人

  private String operator = "";// 操作员

  private String checkpsn = "";

  private String vipcheckpsn = "";

  private String lastcheckpsn = "";

  private String mark_a = "";

  private String loanvipchkDate = "";

  private String lastchkDate = "";

  public String getLoanvipchkDate() {
    return loanvipchkDate;
  }

  public void setLoanvipchkDate(String loanvipchkDate) {
    this.loanvipchkDate = loanvipchkDate;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getAssistantborrowerName() {
    return assistantborrowerName;
  }

  public void setAssistantborrowerName(String assistantborrowerName) {
    this.assistantborrowerName = assistantborrowerName;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(String totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getCheckpsn() {
    return checkpsn;
  }

  public void setCheckpsn(String checkpsn) {
    this.checkpsn = checkpsn;
  }

  public String getVipcheckpsn() {
    return vipcheckpsn;
  }

  public void setVipcheckpsn(String vipcheckpsn) {
    this.vipcheckpsn = vipcheckpsn;
  }

  public BigDecimal getHousePrice() {
    return housePrice;
  }

  public void setHousePrice(BigDecimal housePrice) {
    this.housePrice = housePrice;
  }

  public BigDecimal getLoanMoney_() {
    return loanMoney_;
  }

  public void setLoanMoney_(BigDecimal loanMoney_) {
    this.loanMoney_ = loanMoney_;
  }

  public String getMark_a() {
    return mark_a;
  }

  public void setMark_a(String mark_a) {
    this.mark_a = mark_a;
  }

  public String getLastchkDate() {
    return lastchkDate;
  }

  public void setLastchkDate(String lastchkDate) {
    this.lastchkDate = lastchkDate;
  }

  public String getLastcheckpsn() {
    return lastcheckpsn;
  }

  public void setLastcheckpsn(String lastcheckpsn) {
    this.lastcheckpsn = lastcheckpsn;
  }

}
