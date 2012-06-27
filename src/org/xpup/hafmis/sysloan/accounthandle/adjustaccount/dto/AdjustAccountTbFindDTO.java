package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * 封装了错帐维护查询条件的DTO
 * 
 * @author 付云峰
 */
public class AdjustAccountTbFindDTO {
  /**
   * 贷款帐号
   */
  private String loanKouAcc = "";

  /**
   * 合同编号
   */
  private String contractId = "";

  /**
   * 借款人姓名
   */
  private String borrowerName = "";

  /**
   * 证件号码
   */
  private String cardNum = "";

  /**
   * 凭证编号
   */
  private String docNum = "";

  /**
   * 业务类型
   */
  private String bizType = "";

  /**
   * 放款银行
   */
  private String loanBankId = "";

  /**
   * 业务状态
   */
  private String bizSt = "";

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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
}
