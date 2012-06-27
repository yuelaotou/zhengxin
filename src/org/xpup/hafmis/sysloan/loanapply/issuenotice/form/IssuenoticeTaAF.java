package org.xpup.hafmis.sysloan.loanapply.issuenotice.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;

public class IssuenoticeTaAF extends ActionForm {

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKind = "";// 证件类型

  private String temp_cardKind = "";

  private String cardNum = "";// 证件号码

  private String loanBankId = "";// 放款银行

  private BigDecimal loanMoney = new BigDecimal(0.00);// 借款金额

  private String loanTimeLimit = "";// 借款期限

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// 每月利率

  private BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息

  private String loanMode = "";// 还款方式

  private String temp_loanMode = "";

  private String sellerPayBank = "";// 划款银行

  private String sellerPayBankAcc = "";// 划款银行账号

  private String bizDate = "";// 拨款日期：默认为会计日期

  private String type = "";

  private String isPrint = "";// 是否打印

  private String temp_loanMonthRate = "";

  private String loanKouAcc = "";// 扣款账号

  private String houseTotalPrice = "";// 购房总金额

  private String houseArea = "";// 建筑面积

  private String houseAddr = "";// 购房地址

  private String orgName = "";// 工作单位

  private String orgTel = "";// 联系电话

  private String house = "";// 售房单位

  private BigDecimal firstLoanMoney = new BigDecimal(0.00);// 首次还款金额

  private String bailName = "";

  private String headId = "";

  private String empId = "";

  private String redatePerson = "";

  private String chkAgainPerson = "";

  private String vipChkAgainPerson = "";

  private String lastChkPerson = "";

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

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getBailName() {
    return bailName;
  }

  public void setBailName(String bailName) {
    this.bailName = bailName;
  }

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgTel() {
    return orgTel;
  }

  public void setOrgTel(String orgTel) {
    this.orgTel = orgTel;
  }

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHouseTotalPrice() {
    return houseTotalPrice;
  }

  public void setHouseTotalPrice(String houseTotalPrice) {
    this.houseTotalPrice = houseTotalPrice;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getSellerPayBank() {
    return sellerPayBank;
  }

  public void setSellerPayBank(String sellerPayBank) {
    this.sellerPayBank = sellerPayBank;
  }

  public String getSellerPayBankAcc() {
    return sellerPayBankAcc;
  }

  public void setSellerPayBankAcc(String sellerPayBankAcc) {
    this.sellerPayBankAcc = sellerPayBankAcc;
  }

  public String getTemp_cardKind() {
    return temp_cardKind;
  }

  public void setTemp_cardKind(String temp_cardKind) {
    this.temp_cardKind = temp_cardKind;
  }

  public String getTemp_loanMode() {
    return temp_loanMode;
  }

  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void reset() {

    this.contractId = "";

    this.borrowerName = "";

    this.cardNum = "";

    this.cardKind = "";

    this.loanBankId = "";

    this.loanMoney = null;

    this.loanTimeLimit = "";

    this.loanMonthRate = null;

    this.corpusInterest = null;

    this.loanMode = "";

    this.temp_loanMode = "";

    this.sellerPayBank = "";

    this.sellerPayBankAcc = "";

    this.bizDate = "";
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getIsPrint() {
    return isPrint;
  }

  public void setIsPrint(String isPrint) {
    this.isPrint = isPrint;
  }

  public String getTemp_loanMonthRate() {
    return temp_loanMonthRate;
  }

  public void setTemp_loanMonthRate(String temp_loanMonthRate) {
    this.temp_loanMonthRate = temp_loanMonthRate;
  }
}
