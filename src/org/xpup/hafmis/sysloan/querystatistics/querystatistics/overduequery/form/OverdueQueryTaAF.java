package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class OverdueQueryTaAF extends ActionForm {
  
  private String officeCode;
  
  private String bankId;
  
  private String borrowerName;
  
  private String contractId;
  
  private String cardNum;
  
  private String loanKouAcc;
  
  private String overdueMonthSt;
  
  private String overdueMonthEnd;
  // 公积金协议
  private String agreement;
  
  private Map yesNoMap;
  
  private List list;
  
  private Integer personCount;
  
  private BigDecimal shouldCorpusSum = new BigDecimal(0.00);
  
  private BigDecimal shouldInterestSum = new BigDecimal(0.00);
  
  private BigDecimal shouldPayMoneySum = new BigDecimal(0.00);
  
  private String overdueRate;
  
  private String overdueMoneyRate;

  public String getAgreement() {
    return agreement;
  }

  public void setAgreement(String agreement) {
    this.agreement = agreement;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOverdueMonthEnd() {
    return overdueMonthEnd;
  }

  public void setOverdueMonthEnd(String overdueMonthEnd) {
    this.overdueMonthEnd = overdueMonthEnd;
  }

  public String getOverdueMonthSt() {
    return overdueMonthSt;
  }

  public void setOverdueMonthSt(String overdueMonthSt) {
    this.overdueMonthSt = overdueMonthSt;
  }

  public Map getYesNoMap() {
    return yesNoMap;
  }

  public void setYesNoMap(Map yesNoMap) {
    this.yesNoMap = yesNoMap;
  }

  public Integer getPersonCount() {
    return personCount;
  }

  public void setPersonCount(Integer personCount) {
    this.personCount = personCount;
  }

  public BigDecimal getShouldCorpusSum() {
    return shouldCorpusSum;
  }

  public void setShouldCorpusSum(BigDecimal shouldCorpusSum) {
    this.shouldCorpusSum = shouldCorpusSum;
  }

  public BigDecimal getShouldInterestSum() {
    return shouldInterestSum;
  }

  public void setShouldInterestSum(BigDecimal shouldInterestSum) {
    this.shouldInterestSum = shouldInterestSum;
  }

  public BigDecimal getShouldPayMoneySum() {
    return shouldPayMoneySum;
  }

  public void setShouldPayMoneySum(BigDecimal shouldPayMoneySum) {
    this.shouldPayMoneySum = shouldPayMoneySum;
  }

  public String getOverdueMoneyRate() {
    return overdueMoneyRate;
  }

  public void setOverdueMoneyRate(String overdueMoneyRate) {
    this.overdueMoneyRate = overdueMoneyRate;
  }

  public String getOverdueRate() {
    return overdueRate;
  }

  public void setOverdueRate(String overdueRate) {
    this.overdueRate = overdueRate;
  }
}
