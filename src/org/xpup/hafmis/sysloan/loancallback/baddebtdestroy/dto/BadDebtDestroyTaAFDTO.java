package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;

public class BadDebtDestroyTaAFDTO {
  
  private BorrowerInfoDTO borrowerInfoDTO=new BorrowerInfoDTO();

  // 还至年月
  private List monthYearList = new ArrayList();

  // 应还信息
  private List shouldBackList = new ArrayList();
  
  //还至年月
  private String monthYear = "";

  // 本次总还款本金
  private BigDecimal sumCorpus = new BigDecimal(0.00);

  // 本次总还款利息
  private BigDecimal sumInterest = new BigDecimal(0.00);

  // 本次总还款金额
  private BigDecimal sumMoney = new BigDecimal(0.00);
  
  // 本次实收金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  
  //核销单位类型
  private String orgType = "";
  
  //核销单位名称
  private String orgName = "";
  
  //核销单位编号
  private String loanassistantorgId ="";
  
  //中心委托贷款账号
  private String loanAcc = "";
  
  //中心利息账号
  private String interestAcc = "";
  
  //实还利息
  private BigDecimal realInterest = new BigDecimal(0.00);
  
  //逾期利息
  private BigDecimal overdueInterest = new BigDecimal(0.00);
  
  //实还罚息
  private BigDecimal punishInterest = new BigDecimal(0.00);
  
  //制单人
  private String makeOP = "";
  
  //记账人
  private String clearOP = "";
  
  
  //银行名称
  private String bankName = "";
  
  //业务类型
  private String bizType = "";
  
  //业务日期
  private String bizDate = "";
  
  //凭证号
  private String docNum = "";
  
  //票据号
  private String noteNum = "";
  
  //头表ID
  private String headId = "";
  
  
  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getClearOP() {
    return clearOP;
  }

  public void setClearOP(String clearOP) {
    this.clearOP = clearOP;
  }

  public String getMakeOP() {
    return makeOP;
  }

  public void setMakeOP(String makeOP) {
    this.makeOP = makeOP;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public BigDecimal getOverdueInterest() {
    return overdueInterest;
  }

  public void setOverdueInterest(BigDecimal overdueInterest) {
    this.overdueInterest = overdueInterest;
  }

  public BigDecimal getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
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

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgType() {
    return orgType;
  }

  public void setOrgType(String orgType) {
    this.orgType = orgType;
  }

  public BorrowerInfoDTO getBorrowerInfoDTO() {
    return borrowerInfoDTO;
  }

  public void setBorrowerInfoDTO(BorrowerInfoDTO borrowerInfoDTO) {
    this.borrowerInfoDTO = borrowerInfoDTO;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public List getMonthYearList() {
    return monthYearList;
  }

  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }

  public BigDecimal getRealMoney() {
    return realMoney;
  }

  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public List getShouldBackList() {
    return shouldBackList;
  }

  public void setShouldBackList(List shouldBackList) {
    this.shouldBackList = shouldBackList;
  }

  public BigDecimal getSumCorpus() {
    return sumCorpus;
  }

  public void setSumCorpus(BigDecimal sumCorpus) {
    this.sumCorpus = sumCorpus;
  }

  public BigDecimal getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }

  public BigDecimal getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }

}