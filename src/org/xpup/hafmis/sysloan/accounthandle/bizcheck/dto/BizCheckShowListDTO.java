package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class BizCheckShowListDTO {
  private String flowHeadId = "";// 头表流水号

  private String docNum = "";// 凭证编号

  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String bizType = "";// 转换业务类型
  
  private String originalitybizType = "";// 原始业务类型

  private String wrongBizType = "";//错账调整类型

  private String occurMoney = "";// 发放金额

  private String reclaimCorpus = "";// 回收本金

  private String reclaimAccrual = "";// 回收利息

  private String realPunishInterest = "";// 回收罚息
  
  private String badDebt ="";// 呆账核销金额

  private String putUpMoney = "";// 挂账金额

  private String bail = "";// 保证金

  private String bailAccrual = "";// 保证金利息

  private String bizSt = "";// 业务状态

  private String bizDate = "";// 业务时间
  
  private BigDecimal reclaim = new BigDecimal(0.00);// 本次应还金额
  
  private BigDecimal reclaimback = new BigDecimal(0.00);// 本次实还金额
  
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }


  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
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

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getOriginalitybizType() {
    return originalitybizType;
  }

  public void setOriginalitybizType(String originalitybizType) {
    this.originalitybizType = originalitybizType;
  }

  public BigDecimal getReclaim() {
    return reclaim;
  }

  public void setReclaim(BigDecimal reclaim) {
    this.reclaim = reclaim;
  }

  public BigDecimal getReclaimback() {
    return reclaimback;
  }

  public void setReclaimback(BigDecimal reclaimback) {
    this.reclaimback = reclaimback;
  }
}
