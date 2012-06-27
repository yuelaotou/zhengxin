package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;
import java.math.BigDecimal;
public class LoanaccordDTO {
  private String flowHeadId=""; //pl202头表Id
  
  private String contractId = ""; // 合同编号

  private String borrowerName = ""; // 借款人姓名

  private String cardKind = ""; // 证件类型

  private String cardKindName = ""; // 显示证件类型对应的名称

  private String cardNum = ""; // 证件号码

  private String loanBankId = ""; // 放款银行

  private String loanBankName = ""; // 放款银行名称

  private BigDecimal loanMoney = new BigDecimal(0.00); // 贷款金额

  private String loanTimeLimit = ""; // 贷款期限

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// 每月利率

  private String loanMode = ""; // 还款方式

  private String loanModeName = ""; // 还款方式名称

  private BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息
  
  private String  temploanMonthRate ;// 临时显示用的每月利率

  private String loanKouAcc = "";// 银行扣款账号(贷款账号)

  private String loanStartDate = "";// 发放日期

  private String overTime = "";// 到期日期

  private String loanRepayDay = ""; // 还款日
  
  private BigDecimal firstLoanMoney = new BigDecimal(0.00); // 首次还款金额

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

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
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

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
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

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
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

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }
 
}
