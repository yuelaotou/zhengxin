package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto;

import java.math.BigDecimal;

/**
 * @author 王野 2007-10-15
 */
public class LoanBusiFlowQueryDTO {

  private String flowHeadId = null;// 头表流水号

  private String docNum = "";// 凭证编号

  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String bizType = "";// 业务类型

  private String originalitybizType = null;// 原始业务类型

  private String makePerson = "";// 制单人

  private String bizSt = "";// 业务状态

  private String loanBankName = null;// 放款银行

  private String beginBizDate = null;// 起始办理日期

  private String endBizDate = null;// 终止办理日期

  private String wrongBizType = null;

  private BigDecimal occurMoney = new BigDecimal(0.00);// 发放金额

  private BigDecimal reclaimCorpus = new BigDecimal(0.00);// 回收本金

  private BigDecimal reclaimAccrual = new BigDecimal(0.00);// 回收利息

  private BigDecimal realPunishInterest = new BigDecimal(0.00);// 回收罚息

  private BigDecimal badDebt = new BigDecimal(0.00);// 呆账核销金额

  private BigDecimal reclaim = new BigDecimal(0.00);// 回收总金额

  private BigDecimal putUpMoney = new BigDecimal(0.00);// 挂账金额

  private BigDecimal bail = new BigDecimal(0.00);// 保证金

  private BigDecimal bailAccrual = new BigDecimal(0.00);// 保证金利息
  
  private String isGjjLoanBack;

  private String bizDate = "";// 办理日期

  public BigDecimal getBadDebt() {
    return badDebt;
  }

  public void setBadDebt(BigDecimal badDebt) {
    this.badDebt = badDebt;
  }

  public BigDecimal getBail() {
    return bail;
  }

  public void setBail(BigDecimal bail) {
    this.bail = bail;
  }

  public BigDecimal getBailAccrual() {
    return bailAccrual;
  }

  public void setBailAccrual(BigDecimal bailAccrual) {
    this.bailAccrual = bailAccrual;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
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

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public String getOriginalitybizType() {
    return originalitybizType;
  }

  public void setOriginalitybizType(String originalitybizType) {
    this.originalitybizType = originalitybizType;
  }

  public BigDecimal getPutUpMoney() {
    return putUpMoney;
  }

  public void setPutUpMoney(BigDecimal putUpMoney) {
    this.putUpMoney = putUpMoney;
  }

  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }

  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public BigDecimal getReclaim() {
    return reclaim;
  }

  public void setReclaim(BigDecimal reclaim) {
    this.reclaim = reclaim;
  }

  public BigDecimal getReclaimAccrual() {
    return reclaimAccrual;
  }

  public void setReclaimAccrual(BigDecimal reclaimAccrual) {
    this.reclaimAccrual = reclaimAccrual;
  }

  public BigDecimal getReclaimCorpus() {
    return reclaimCorpus;
  }

  public void setReclaimCorpus(BigDecimal reclaimCorpus) {
    this.reclaimCorpus = reclaimCorpus;
  }

  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getIsGjjLoanBack() {
    return isGjjLoanBack;
  }

  public void setIsGjjLoanBack(String isGjjLoanBack) {
    this.isGjjLoanBack = isGjjLoanBack;
  }

  
}
