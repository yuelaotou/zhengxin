package org.xpup.hafmis.sysloan.loancallback.destoryback.dto;

import java.math.BigDecimal;

public class DestoryBackTbDTO {
  private String loanKouAcc = "";// 贷款账号
  
  private String docNum = "";// 凭证编号
  
  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名
  
  private String cardNum = "";// 证件号码
  
  private BigDecimal reclaimCorpus = new BigDecimal(0.00);// 回收金额
  
  private String bizSt = "";// 业务状态
  
  private String flowHeadId = "";// 流水头表ID

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
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


  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public BigDecimal getReclaimCorpus() {
    return reclaimCorpus;
  }

  public void setReclaimCorpus(BigDecimal reclaimCorpus) {
    this.reclaimCorpus = reclaimCorpus;
  }
}
