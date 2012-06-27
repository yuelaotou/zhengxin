package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

public class EndorsecontractTeDTO {
  private String isContract = "";//是否能签订合同，控制页面上的不允许签订按钮是否可用
  private String bankid = "";//给hanl用的，不在页面上显示，只是判断不允许签订按钮是否可用
  private String contractId = "";//合同编号
  private String debitter = "";//借款人姓名 PL110 
  private String empId = "";//职工编号
  private String loanMoney = "";//借款金额
  private String startDate = "";//开始时间
  private String endDate = "";//结束时间
  private String loanTimeLimit = "";//借款期限
  private String loanMonthRate = "";//月利率
  private String corpusInterest = "";//月还本息
  private String bank = "";//放款银行
  private String scanInfo = "";//查看扫描信息
  private String contractStatus = "";// 合同状态(是否签订合同)
  private String isWrite = "";//是否签定
  private String temploanMonthRate = "";//other  月利率
  private String loanMode="";//还款方式
  private String entireYearMoney="";//整年期总还款额
  private String contractSt = "";// 合同状态(1.申请，2.提交审核，3.审核通过等)
  private String strContractSt = "";//显示中文的合同状态
  private String fundStatus = "";
  private String photoUrl = "";//
  
  public String getPhotoUrl() {
    return photoUrl;
  }
  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }
  public String getFundStatus() {
    return fundStatus;
  }
  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }
  public String getEntireYearMoney() {
    return entireYearMoney;
  }
  public void setEntireYearMoney(String entireYearMoney) {
    this.entireYearMoney = entireYearMoney;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }
  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }
  public String getIsWrite() {
    return isWrite;
  }
  public void setIsWrite(String isWrite) {
    this.isWrite = isWrite;
  }
  public String getContractStatus() {
    return contractStatus;
  }
  public void setContractStatus(String contractStatus) {
    this.contractStatus = contractStatus;
  }
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getDebitter() {
    return debitter;
  }
  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }
  public String getLoanMonthRate() {
    return loanMonthRate;
  }
  public void setLoanMonthRate(String loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public String getScanInfo() {
    return scanInfo;
  }
  public void setScanInfo(String scanInfo) {
    this.scanInfo = scanInfo;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getIsContract() {
    return isContract;
  }
  public void setIsContract(String isContract) {
    this.isContract = isContract;
  }
  public String getBankid() {
    return bankid;
  }
  public void setBankid(String bankid) {
    this.bankid = bankid;
  }
  public String getContractSt() {
    return contractSt;
  }
  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }
  public String getStrContractSt() {
    return strContractSt;
  }
  public void setStrContractSt(String strContractSt) {
    this.strContractSt = strContractSt;
  }
  
  
  
}
