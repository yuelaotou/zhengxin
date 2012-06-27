package org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto;

import java.math.BigDecimal;

public class LoanaccordDTO {
  private String contractId = ""; // 合同编号

  private String borrowerName = ""; // 借款人姓名

  private String cardKind = ""; // 证件类型

  private String cardKindName = ""; // 显示证件类型对应的名称

  private String cardNum = ""; // 证件号码

  private String loanBankId = ""; // 放款银行

  private String loanBankName = ""; // 放款银行名称

  private String office = ""; // 银行对应的办事处

  private BigDecimal loanMoney = new BigDecimal(0.00); // 借款金额

  private String loanTimeLimit = ""; // 贷款期限

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// 每月利率
  
  private String  temploanMonthRate ;// 临时显示用的每月利率

  private String loanMode = ""; // 还款方式

  private String loanModeName = ""; // 还款方式名称

  private BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息

  private String loanKouAcc = "";// 银行收款账号(贷款账号)

  private String loanStartDate = "";// 发放日期

  private String overTime = "";// 到期日期

  private String loanRepayDay = ""; // 还款日

  private String loanRepayDayInfo = ""; // 还款日方式：按户定日，统一定日

  private String loanRateType = ""; // 贷款类型

  private String bizSt = ""; // pl202业务状态
  
  private String bizStName = ""; // pl202业务状态
  
  private Integer flowHeadId;   // pl202的头表id
  
  private BigDecimal firstLoanMoney = new BigDecimal(0.00);// 首月还款金额
  
  private String operson="";   //经手人
  
  private String bizDate="";   //打印的会计日期
  
  private String docNum="";   //凭证号
  
  private BigDecimal empId = new BigDecimal(0.00);// 职工编号
  
  private BigDecimal orgId = new BigDecimal(0.00);//单位编号
  
  private String isEntire = "";//是否是整年期还贷
  
  private  BigDecimal interestTotal = new BigDecimal(0.00);// 应还利息合计
  
  private String noteNum = "";//结算号
 
  
  

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public BigDecimal getEmpId() {
    return empId;
  }

  public void setEmpId(BigDecimal empId) {
    this.empId = empId;
  }

  public BigDecimal getOrgId() {
    return orgId;
  }

  public void setOrgId(BigDecimal orgId) {
    this.orgId = orgId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getOperson() {
    return operson;
  }

  public void setOperson(String operson) {
    this.operson = operson;
  }

//  public BigDecimal getFirstLoanMoney() {
//    return firstLoanMoney;
//  }
//
//  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
//    this.firstLoanMoney = firstLoanMoney;
//  }

  public Integer getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanRateType() {
    return loanRateType;
  }

  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }

  public String getLoanRepayDayInfo() {
    return loanRepayDayInfo;
  }

  public void setLoanRepayDayInfo(String loanRepayDayInfo) {
    this.loanRepayDayInfo = loanRepayDayInfo;
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

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
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

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOverTime() {
    return overTime;
  }

  public void setOverTime(String overTime) {
    this.overTime = overTime;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizStName() {
    return bizStName;
  }

  public void setBizStName(String bizStName) {
    this.bizStName = bizStName;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
  }

  public String getIsEntire() {
    return isEntire;
  }

  public void setIsEntire(String isEntire) {
    this.isEntire = isEntire;
  }

}
