package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * 封装了弹出框中查询条件的DTO
 * 
 * @author 付云峰
 */
public class AdjustAccountPopFindDTO {
  
  /**
   * 合同编号
   */
  private String contractId = "";
  /**
   * 贷款帐号
   */
  private String loanKouAcc = "";
  /**
   * 业务日期
   */
  private String bizDate = "";
  /**
   * 业务类型
   */
  private String bizType = "";
  /**
   * 放款银行
   */
  private String loanBankId = "";
  /**
   * 借款人姓名
   */
  private String borrowerName = "";
  
  /**
   * 凭证号
   */
  private String docNum = "";
  
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
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  
  
}
