package org.xpup.hafmis.sysloan.accounthandle.overpay.dto;

import java.math.BigDecimal;

public class OverPayTbShowListDTO {
  private String loankouacc="";//贷款账号
  private String docNum="";//凭证编号
  private String contractId="";//合同编号
  private String borrowerName="";//借款人姓名
  private String cardNum="";//证件号码
  private BigDecimal occurMoney=new BigDecimal(0.00);//挂账发生额
  private String bizSt="";//业务状态
  private String temp_bizSt="";
  private String flowHeadId="";//202表的id
  
  public String getFlowHeadId() {
    return flowHeadId;
  }
  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }
  public String getTemp_bizSt() {
    return temp_bizSt;
  }
  public void setTemp_bizSt(String temp_bizSt) {
    this.temp_bizSt = temp_bizSt;
  }
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
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
}
