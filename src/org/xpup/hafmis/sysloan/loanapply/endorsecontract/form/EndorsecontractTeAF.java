package org.xpup.hafmis.sysloan.loanapply.endorsecontract.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EndorsecontractTeAF extends ActionForm{

  private String contractId = "";//合同编号
  private String debitter = "";//借款人姓名 PL110 
  private String empId = "";//职工编号
  private String cardNum = "";//证件号码
  private String bank = "";//放款银行
  private String loanTimeLimit = "";//借款期限
  private String startSDate = "";//起始1日期
  private String startEDate = "";//起始2日期
  private String endSDate = "";//结束日期
  private String endEDate = "";//结束日期
  private String contractSt = "";//合同状态
  private String fundStatus = "";
  
  private List list = new ArrayList();//
  private List bankList = new ArrayList();//
  private Map contractstMap = new HashMap();
  

  
  public String getContractSt() {
    return contractSt;
  }
  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public List getBankList() {
    return bankList;
  }
  public void setBankList(List bankList) {
    this.bankList = bankList;
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
  public String getEndEDate() {
    return endEDate;
  }
  public void setEndEDate(String endEDate) {
    this.endEDate = endEDate;
  }
  public String getEndSDate() {
    return endSDate;
  }
  public void setEndSDate(String endSDate) {
    this.endSDate = endSDate;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public String getStartEDate() {
    return startEDate;
  }
  public void setStartEDate(String startEDate) {
    this.startEDate = startEDate;
  }
  public String getStartSDate() {
    return startSDate;
  }
  public void setStartSDate(String startSDate) {
    this.startSDate = startSDate;
  }
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.bank="";
    this.bankList=null;
    this.cardNum="";
    this.contractId="";
    this.debitter="";
    this.empId="";
    this.endEDate="";
    this.endSDate="";
    this.list=null;
    this.startEDate="";
    this.startSDate="";
  }
  public Map getContractstMap() {
    return contractstMap;
  }
  public void setContractstMap(Map contractstMap) {
    this.contractstMap = contractstMap;
  }
  public String getFundStatus() {
    return fundStatus;
  }
  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }
  
}
