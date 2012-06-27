package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto;

import java.math.BigDecimal;

public class OverDueinfoQueryShowListDTO {
  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKind = ""; // 证件类型

  private String cardNum = ""; // 证件号码

  private String orgName = ""; // 单位名称

  private String houseTel = ""; // 住宅电话

  private String homeMobile = ""; // 移动电话

  private String orgTel = ""; // 单位电话

  private BigDecimal oweCorpus = new BigDecimal(0.00); // 欠还本金

  private BigDecimal oweInterest = new BigDecimal(0.00); // 欠还利息

  private BigDecimal punishInterest = new BigDecimal(0.00); // 欠还罚息利息

  private String oweMonth = ""; // 逾期月数
  
  private String oweDate="";//逾期年月
  
  private String id="";//pl205ID
  private String loan_money="";
  private String loan_time_limit="";
  private String overplus_loan_money="";
  private String name="";
  private String card_num="";
  private String org_name="";
  private String home_mobile="";
  private String org_tel="";
  private String home_addr="";
  private String agreement="";
  private String applyDate="";
  private String corpusInterestAll="";
  private String nextCorpusInterest="";
  private String nextMoney="";
  
  public String getAgreement() {
    return agreement;
  }

  public void setAgreement(String agreement) {
    this.agreement = agreement;
  }

  public String getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(String applyDate) {
    this.applyDate = applyDate;
  }

  public String getCard_num() {
    return card_num;
  }

  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }

  public String getHome_addr() {
    return home_addr;
  }

  public void setHome_addr(String home_addr) {
    this.home_addr = home_addr;
  }

  public String getHome_mobile() {
    return home_mobile;
  }

  public void setHome_mobile(String home_mobile) {
    this.home_mobile = home_mobile;
  }

  public String getLoan_money() {
    return loan_money;
  }

  public void setLoan_money(String loan_money) {
    this.loan_money = loan_money;
  }

  public String getLoan_time_limit() {
    return loan_time_limit;
  }

  public void setLoan_time_limit(String loan_time_limit) {
    this.loan_time_limit = loan_time_limit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrg_name() {
    return org_name;
  }

  public void setOrg_name(String org_name) {
    this.org_name = org_name;
  }

  public String getOrg_tel() {
    return org_tel;
  }

  public void setOrg_tel(String org_tel) {
    this.org_tel = org_tel;
  }

  public String getOverplus_loan_money() {
    return overplus_loan_money;
  }

  public void setOverplus_loan_money(String overplus_loan_money) {
    this.overplus_loan_money = overplus_loan_money;
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

  public String getHomeMobile() {
    return homeMobile;
  }

  public void setHomeMobile(String homeMobile) {
    this.homeMobile = homeMobile;
  }

  public String getHouseTel() {
    return houseTel;
  }

  public void setHouseTel(String houseTel) {
    this.houseTel = houseTel;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
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
  public String getOweMonth() {
    return oweMonth;
  }

  public void setOweMonth(String oweMonth) {
    this.oweMonth = oweMonth;
  }

  public BigDecimal getOweCorpus() {
    return oweCorpus;
  }

  public void setOweCorpus(BigDecimal oweCorpus) {
    this.oweCorpus = oweCorpus;
  }

  public BigDecimal getOweInterest() {
    return oweInterest;
  }

  public void setOweInterest(BigDecimal oweInterest) {
    this.oweInterest = oweInterest;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public String getOweDate() {
    return oweDate;
  }

  public void setOweDate(String oweDate) {
    this.oweDate = oweDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNextCorpusInterest() {
    return nextCorpusInterest;
  }

  public void setNextCorpusInterest(String nextCorpusInterest) {
    this.nextCorpusInterest = nextCorpusInterest;
  }

  public String getNextMoney() {
    return nextMoney;
  }

  public void setNextMoney(String nextMoney) {
    this.nextMoney = nextMoney;
  }

  public String getCorpusInterestAll() {
    return corpusInterestAll;
  }

  public void setCorpusInterestAll(String corpusInterestAll) {
    this.corpusInterestAll = corpusInterestAll;
  }

}
