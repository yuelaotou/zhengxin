package org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BailpickupTbDTO {
  
  private String id = "";//流水头表ID
  private String loanKouAcc = "";//贷款账号
  private String contractId = "";//合同编号
  private String borrowerName = "";//借款人姓名
  private String cardNum = "";//证件号码
  private String occurMoney = "";//提取保证金金额
  private String otherInterest = "";//提取利息
  private String pickupMoney = "";//提取金额
  
  private List bankList = new ArrayList();//放款银行下拉框
  private Map map = new HashMap();//业务状态
  private List list = new ArrayList();
  
  private String sumOccurMoney = "";//合计提取保证金金额
  private String sumOtherInterest = "";//合计提取利息
  private String sumPickupMoney = "";//合计提取金额
  
  public String getSumOccurMoney() {
    return sumOccurMoney;
  }
  public void setSumOccurMoney(String sumOccurMoney) {
    this.sumOccurMoney = sumOccurMoney;
  }
  public String getSumOtherInterest() {
    return sumOtherInterest;
  }
  public void setSumOtherInterest(String sumOtherInterest) {
    this.sumOtherInterest = sumOtherInterest;
  }
  public String getSumPickupMoney() {
    return sumPickupMoney;
  }
  public void setSumPickupMoney(String sumPickupMoney) {
    this.sumPickupMoney = sumPickupMoney;
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
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }
  public String getOtherInterest() {
    return otherInterest;
  }
  public void setOtherInterest(String otherInterest) {
    this.otherInterest = otherInterest;
  }
  public String getPickupMoney() {
    return pickupMoney;
  }
  public void setPickupMoney(String pickupMoney) {
    this.pickupMoney = pickupMoney;
  }
  public List getBankList() {
    return bankList;
  }
  public void setBankList(List bankList) {
    this.bankList = bankList;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
}
