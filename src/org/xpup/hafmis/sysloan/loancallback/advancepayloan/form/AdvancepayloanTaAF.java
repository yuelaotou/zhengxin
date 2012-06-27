package org.xpup.hafmis.sysloan.loancallback.advancepayloan.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class AdvancepayloanTaAF extends ActionForm {

  private String loanKouAcc = "";// 贷款帐号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKind = "";// 证件类型

  private String cardNum = "";// 证件号码

  private String corpusInterest = "";// 月还本息

  private String overplusLoanMoney = "";// 剩余本金 OVERPLUS_LOAN_MONEY

  private String creditType = "";// 还款方式

  private String pre_term = "";// 原借款期限

  private String new_term = "";// 新借款期限

  private String type = "";// 类型

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

  public String getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getCreditType() {
    return creditType;
  }

  public void setCreditType(String creditType) {
    this.creditType = creditType;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getNew_term() {
    return new_term;
  }

  public void setNew_term(String new_term) {
    this.new_term = new_term;
  }

  public String getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(String overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getPre_term() {
    return pre_term;
  }

  public void setPre_term(String pre_term) {
    this.pre_term = pre_term;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
