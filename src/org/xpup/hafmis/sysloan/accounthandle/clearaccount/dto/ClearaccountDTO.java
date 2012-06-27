package org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto;

public class ClearaccountDTO {
  private String flowHeadId = null;// 头表流水号

  private String docNum = null;// 凭证编号

  private String loanKouAcc = null;// 贷款账号

  private String contractId = null;// 合同编号

  private String borrowerName = null;// 借款人姓名

  private String bizType = null;// 转换业务类型

  private String originalitybizType = null;// 原始业务类型

  private String wrongBizType = null;

  private String occurMoney = null;// 发放金额

  private String reclaimCorpus = null;// 回收本金

  private String reclaimAccrual = null;// 回收利息

  private String realPunishInterest = null;// 回收罚息

  private String badDebt = null;// 呆账核销金额

  private String putUpMoney = null;// 挂账金额

  private String bail = null;// 保证金

  private String bailAccrual = null;// 保证金利息

  private String bizSt = null;// 业务状态

  private String bizDate = null;// 业务时间
  
  private String sumReclaimMoney=""; //回收金额
  
  private String realCorpus = null;//实还本金
  
  private String realInterest = null;//实还利息
  
  private String realPunish_interest = null;//实还罚息
  
  private String loanBank = null;//放款银行(委托银行)
  
  private String makeBillPerson = "";
  
  private String checkPerson = "";
  
  private String clearAccPerson = "";
  
  private String isGjjLoanBack = "";

  public String getCheckPerson() {
    return checkPerson;
  }

  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }

  public String getClearAccPerson() {
    return clearAccPerson;
  }

  public void setClearAccPerson(String clearAccPerson) {
    this.clearAccPerson = clearAccPerson;
  }

  public String getIsGjjLoanBack() {
    return isGjjLoanBack;
  }

  public void setIsGjjLoanBack(String isGjjLoanBack) {
    this.isGjjLoanBack = isGjjLoanBack;
  }

  public String getMakeBillPerson() {
    return makeBillPerson;
  }

  public void setMakeBillPerson(String makeBillPerson) {
    this.makeBillPerson = makeBillPerson;
  }

  public String getSumReclaimMoney() {
    return sumReclaimMoney;
  }

  public void setSumReclaimMoney(String sumReclaimMoney) {
    this.sumReclaimMoney = sumReclaimMoney;
  }

  public String getBadDebt() {
    return badDebt;
  }

  public void setBadDebt(String badDebt) {
    this.badDebt = badDebt;
  }

  public String getBail() {
    return bail;
  }

  public void setBail(String bail) {
    this.bail = bail;
  }

  public String getBailAccrual() {
    return bailAccrual;
  }

  public void setBailAccrual(String bailAccrual) {
    this.bailAccrual = bailAccrual;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
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

  public String getOriginalitybizType() {
    return originalitybizType;
  }

  public void setOriginalitybizType(String originalitybizType) {
    this.originalitybizType = originalitybizType;
  }

  public String getPutUpMoney() {
    return putUpMoney;
  }

  public void setPutUpMoney(String putUpMoney) {
    this.putUpMoney = putUpMoney;
  }

  public String getRealPunishInterest() {
    return realPunishInterest;
  }

  public void setRealPunishInterest(String realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public String getReclaimAccrual() {
    return reclaimAccrual;
  }

  public void setReclaimAccrual(String reclaimAccrual) {
    this.reclaimAccrual = reclaimAccrual;
  }

  public String getReclaimCorpus() {
    return reclaimCorpus;
  }

  public void setReclaimCorpus(String reclaimCorpus) {
    this.reclaimCorpus = reclaimCorpus;
  }

  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public String getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(String realCorpus) {
    this.realCorpus = realCorpus;
  }

  public String getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }

  public String getRealPunish_interest() {
    return realPunish_interest;
  }

  public void setRealPunish_interest(String realPunish_interest) {
    this.realPunish_interest = realPunish_interest;
  }


}
