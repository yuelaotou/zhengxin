package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.HashMap;
import java.util.Map;

public class EndorsecontractTaDTO {
  private Map beentrusterMap = new HashMap();

  private String contractId = ""; // 合同ID

  private String entruster = ""; // 委托方 (甲方 ××中心)

  private String beentruster = "";// 受托方

  private String assurer = "";// 保证方

  private String debitter = "";// 借款方

  private String certificateType = "";// 证件类型

  private String certificateNum = "";// 证件号码

  private String debitMoney = "";// 借款金额

  private String term = "";// 借款期限

  private String monthInterest = "";// 每月利率

  private String creditType = "";// 还款方式

  private String contractSureDate = "";// 合同签订日期

  private String debitMoneyStaDate = "";// 借款起始日期

  private String debitMoneyEndDate = "";// 借款终止日期

  private String assistantOrgId = "";// 担保公司编号

  private String office = "";// 办事处

  private String photoUrl = "";// 路径

  private String corpusInterest = "";// 月还本息

  private String isWrite = "";// 是否签订合同

  private String contractSt = "";// 合同状态

  private String loanKouAcc = "";

  private String paramValue = "";// 参数值AB or BA

  private String writeType = "";// 用于判断是否签订合同后设置的类型

  private String isComeFromT5 = "";// 用于判断是否来自维护的类型

  public String getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getAssurer() {
    return assurer;
  }

  public void setAssurer(String assurer) {
    this.assurer = assurer;
  }

  public String getBeentruster() {
    return beentruster;
  }

  public void setBeentruster(String beentruster) {
    this.beentruster = beentruster;
  }

  public Map getBeentrusterMap() {
    return beentrusterMap;
  }

  public void setBeentrusterMap(Map beentrusterMap) {
    this.beentrusterMap = beentrusterMap;
  }

  public String getCertificateNum() {
    return certificateNum;
  }

  public void setCertificateNum(String certificateNum) {
    this.certificateNum = certificateNum;
  }

  public String getCertificateType() {
    return certificateType;
  }

  public void setCertificateType(String certificateType) {
    this.certificateType = certificateType;
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

  public String getContractSureDate() {
    return contractSureDate;
  }

  public void setContractSureDate(String contractSureDate) {
    this.contractSureDate = contractSureDate;
  }

  public String getCreditType() {
    return creditType;
  }

  public void setCreditType(String creditType) {
    this.creditType = creditType;
  }

  public String getDebitMoney() {
    return debitMoney;
  }

  public void setDebitMoney(String debitMoney) {
    this.debitMoney = debitMoney;
  }

  public String getDebitMoneyEndDate() {
    return debitMoneyEndDate;
  }

  public void setDebitMoneyEndDate(String debitMoneyEndDate) {
    this.debitMoneyEndDate = debitMoneyEndDate;
  }

  public String getDebitMoneyStaDate() {
    return debitMoneyStaDate;
  }

  public void setDebitMoneyStaDate(String debitMoneyStaDate) {
    this.debitMoneyStaDate = debitMoneyStaDate;
  }

  public String getDebitter() {
    return debitter;
  }

  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }

  public String getEntruster() {
    return entruster;
  }

  public void setEntruster(String entruster) {
    this.entruster = entruster;
  }

  public String getIsComeFromT5() {
    return isComeFromT5;
  }

  public void setIsComeFromT5(String isComeFromT5) {
    this.isComeFromT5 = isComeFromT5;
  }

  public String getIsWrite() {
    return isWrite;
  }

  public void setIsWrite(String isWrite) {
    this.isWrite = isWrite;
  }

  public String getMonthInterest() {
    return monthInterest;
  }

  public void setMonthInterest(String monthInterest) {
    this.monthInterest = monthInterest;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getWriteType() {
    return writeType;
  }

  public void setWriteType(String writeType) {
    this.writeType = writeType;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
}
