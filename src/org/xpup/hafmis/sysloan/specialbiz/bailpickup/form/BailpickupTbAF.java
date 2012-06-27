package org.xpup.hafmis.sysloan.specialbiz.bailpickup.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BailpickupTbAF extends ActionForm{
  
 
  private String loanKouAcc = "";//贷款账号
  private String contractId = "";//合同编号
  private String borrowerName = "";//借款人姓名
  private String cardNum = "";//证件号码
  private String loanBank = "";//放款银行
  private String bizStatus = "";//业务状态
  
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getBizStatus() {
    return bizStatus;
  }
  public void setBizStatus(String bizStatus) {
    this.bizStatus = bizStatus;
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
  public String getLoanBank() {
    return loanBank;
  }
  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.loanKouAcc = "";
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.loanBank = "";
    this.bizStatus = "";
  }

  
}
