package org.xpup.hafmis.sysloan.loanapply.loancheck.dto;

public class LoanCheckDTO {

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardNum = "";// 证件号码

  private String loanMoney = "";// 借款金额（万元）（小数点保留一位）

  private String loanTimeLimit = "";// 借款期限

  private String loanBankName = "";// 放款银行

  private String orgName = "";// 单位名称

  private String houseArea = "";// 建筑面积（M2）（m2）（小数点后保留两位）

  private String houseType = "";// 购房类型

  private String contractSt = "";// 合同状态

  private String beginBizDate = null;// 起始操作时间

  private String endBizDate = null;// 终止操作时间

  private String developerName = "";

  private String assistantborrowerName = null;// 配偶姓名

  private String totlePrice = "";// 房价（元s)（小数点保留两位)

  private String houseAddr = "";// 房屋坐落

  private String remark = "";// 备注

  private String operator = "";// 操作员

  private String office = "";

  private String reDate = "";// 回件时间

  private String photoUrl = "";// 扫描的图片路径

  private String isContract_write = "";// 是否签订合同

  private String vipcheckDate = "";// 审批时间

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getReDate() {
    return reDate;
  }

  public void setReDate(String reDate) {
    this.reDate = reDate;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getAssistantborrowerName() {
    return assistantborrowerName;
  }

  public void setAssistantborrowerName(String assistantborrowerName) {
    this.assistantborrowerName = assistantborrowerName;
  }

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getTotlePrice() {
    return totlePrice;
  }

  public void setTotlePrice(String totlePrice) {
    this.totlePrice = totlePrice;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
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

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
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

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getIsContract_write() {
    return isContract_write;
  }

  public void setIsContract_write(String isContract_write) {
    this.isContract_write = isContract_write;
  }

  public String getVipcheckDate() {
    return vipcheckDate;
  }

  public void setVipcheckDate(String vipcheckDate) {
    this.vipcheckDate = vipcheckDate;
  }

}
