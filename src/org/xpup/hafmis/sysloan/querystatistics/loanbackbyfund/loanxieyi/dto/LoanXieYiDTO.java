package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.dto;



/**
 * @author 王野 2007-10-15
 */
public class LoanXieYiDTO {

  private String xieYiNum = null;// 头表流水号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String borrowerCardNum = "";// 业务类型

  private String borrowerEmpId = null;// 原始业务类型

  private String fuzhuName = "";// 制单人

  private String fuzhuCardNum = "";// 业务状态

  private String fuzhuEmpId = null;// 放款银行

  private String bizDate = null;// 起始办理日期

  private String stopDate= null;// 终止办理日期

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getFuzhuCardNum() {
    return fuzhuCardNum;
  }

  public void setFuzhuCardNum(String fuzhuCardNum) {
    this.fuzhuCardNum = fuzhuCardNum;
  }

  public String getFuzhuEmpId() {
    return fuzhuEmpId;
  }

  public void setFuzhuEmpId(String fuzhuEmpId) {
    this.fuzhuEmpId = fuzhuEmpId;
  }

  public String getFuzhuName() {
    return fuzhuName;
  }

  public void setFuzhuName(String fuzhuName) {
    this.fuzhuName = fuzhuName;
  }

  public String getStopDate() {
    return stopDate;
  }

  public void setStopDate(String stopDate) {
    this.stopDate = stopDate;
  }

  public String getXieYiNum() {
    return xieYiNum;
  }

  public void setXieYiNum(String xieYiNum) {
    this.xieYiNum = xieYiNum;
  }
  
}
