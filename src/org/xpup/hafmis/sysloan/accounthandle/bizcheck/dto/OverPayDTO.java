package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class OverPayDTO {
  private String flowHeadId = ""; // pl202头表Id

  private String loankouacc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKind = "";// 证件类型

  private String cardKindName = "";

  private String cardNum = "";// 证件号码

  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);// 剩余本金

  private String overplusLimite = "";// 剩余期限

  private String loanMode = "";// 还款方式

  private String loanModeName = "";// 还款方式

  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额

  private BigDecimal overpayMoney = new BigDecimal(0.00);// 挂账金额
  
  private String clearAccPerson = "";//记账人
  
  private String checkPerson = "";//复核人
  
  
  private String docNum="";
  
  private String noteNum="";
  

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

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }

  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }

  public String getOverplusLimite() {
    return overplusLimite;
  }

  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;

  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public BigDecimal getOverpayMoney() {
    return overpayMoney;
  }

  public void setOverpayMoney(BigDecimal overpayMoney) {
    this.overpayMoney = overpayMoney;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
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
}