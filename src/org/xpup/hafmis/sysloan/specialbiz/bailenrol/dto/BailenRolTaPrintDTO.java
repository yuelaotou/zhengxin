/**
 * Copy Right Information   : Goldsoft 
 * Project                  : BailenRolTaPrintDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   : 2007-10-30
 **/
package org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto;

import java.math.BigDecimal;

public class BailenRolTaPrintDTO {

  private String contractId = ""; // 合同ID

  private String borrowerName = ""; // 借款人姓名

  private String cardKind = ""; // 证件类型

  private String cardNum = ""; // 证件号码

  private String loanBankName = ""; // 收款银行

  private String loanKouAcc = ""; // 收款银行帐号

  private String bizDate = ""; // 收取日期

  private String docNum = ""; // 凭证号

  private String noteNum = ""; // 票据号
  
  private String userName = ""; //制单人
  
  private String userBizDate = "";// 业务日期

  private BigDecimal occurMoney = new BigDecimal(0.00);// 保证金金额

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
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

  public String getUserBizDate() {
    return userBizDate;
  }

  public void setUserBizDate(String userBizDate) {
    this.userBizDate = userBizDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
