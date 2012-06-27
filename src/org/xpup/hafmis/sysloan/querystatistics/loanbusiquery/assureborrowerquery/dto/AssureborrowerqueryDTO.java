package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.dto;

import java.io.Serializable;

/**
 * 张列
 * @author Administrator
 *
 */
public class AssureborrowerqueryDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  

  /*
   * 合同编号
   */
  private String contractId = "";

  /*
   * 贷款帐号
   */
  private String loanKouAcc = "";

  /*
   * 借款人姓名
   */
  private String borrowerName = "";

  /*
   * 借款人职工编号
   */
  private String borrowerEmpId = "";

  /*
   * 借款人单位
   */
  private String borrowerOrgName = "";

  /*
   * 借款人证件号码
   */
  private String borrowerCardNum = "";

  /*
   * （辅助、担保）借款人姓名
   */
  private String otherBorrowerName = "";

  /*
   * （辅助、担保）借款人职工编号
   */
  private String otherBorrowerEmpId = "";

  /*
   * （辅助、担保）借款人单位
   */
  private String otherBorrowerOrgName = "";

  /*
   * 贷款金额
   */
  private String loanMoney = "";

  /*
   * 贷款余额
   */
  private String overPlusMoney = "";

  /*
   * 合同状态
   */
  private String contractSt = "";

  /*
   * 贷款期限
   */
  private String loanTimeLimit = "";
  
  /*
   * 类型
   */
  private String type="";
  /*
   * 单位编号
   */
  private String orgId="";

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBorrowerCardNum() {
    return borrowerCardNum;
  }

  public void setBorrowerCardNum(String borrowerCardNum) {
    this.borrowerCardNum = borrowerCardNum;
  }

  public String getBorrowerEmpId() {
    return borrowerEmpId;
  }

  public void setBorrowerEmpId(String borrowerEmpId) {
    this.borrowerEmpId = borrowerEmpId;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getBorrowerOrgName() {
    return borrowerOrgName;
  }

  public void setBorrowerOrgName(String borrowerOrgName) {
    this.borrowerOrgName = borrowerOrgName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOtherBorrowerEmpId() {
    return otherBorrowerEmpId;
  }

  public void setOtherBorrowerEmpId(String otherBorrowerEmpId) {
    this.otherBorrowerEmpId = otherBorrowerEmpId;
  }

  public String getOtherBorrowerName() {
    return otherBorrowerName;
  }

  public void setOtherBorrowerName(String otherBorrowerName) {
    this.otherBorrowerName = otherBorrowerName;
  }

  public String getOtherBorrowerOrgName() {
    return otherBorrowerOrgName;
  }

  public void setOtherBorrowerOrgName(String otherBorrowerOrgName) {
    this.otherBorrowerOrgName = otherBorrowerOrgName;
  }

  public String getOverPlusMoney() {
    return overPlusMoney;
  }

  public void setOverPlusMoney(String overPlusMoney) {
    this.overPlusMoney = overPlusMoney;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
 
}
