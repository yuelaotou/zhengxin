package org.xpup.hafmis.sysloan.loancallback.destoryback.dto;

import java.math.BigDecimal;

public class DestoryBackTaDTO {

  private String flowHeadId = ""; // pl202头表Id
  
  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名
  
  private String cardKind = ""; // 证件类型

  private String cardKindName = ""; // 显示证件类型对应的名称

  private String cardNum = ""; // 证件号码
  
  private BigDecimal overplusLoanMoney=new BigDecimal(0.00);//剩余本金
  
  private String loanMode="";//还款方式
  
  private String loanModeName="";//还款方式
  
  private BigDecimal noBackMoney=new BigDecimal(0.00);//核销未收回金额
  
  private BigDecimal backMoney=new BigDecimal(0.00);//收回金额
  
  private String backUnit= "";//收回单位
  
  private String backunitName= "";//收回单位名称
  
  private String loanassistantorgId= "";// 收回单位ID

  public BigDecimal getBackMoney() {
    return backMoney;
  }

  public void setBackMoney(BigDecimal backMoney) {
    this.backMoney = backMoney;
  }

  public String getBackUnit() {
    return backUnit;
  }

  public void setBackUnit(String backUnit) {
    this.backUnit = backUnit;
  }

  public String getBackunitName() {
    return backunitName;
  }

  public void setBackunitName(String backunitName) {
    this.backunitName = backunitName;
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

  public BigDecimal getNoBackMoney() {
    return noBackMoney;
  }

  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }
  
}
