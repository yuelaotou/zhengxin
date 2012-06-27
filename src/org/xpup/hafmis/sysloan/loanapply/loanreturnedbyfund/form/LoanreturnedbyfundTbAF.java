package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class LoanreturnedbyfundTbAF extends ActionForm {
  private String contractId = "";

  private String borrowerName = "";

  private String cardNum = "";

  private String xieYiNum = "";

  private String assiBorrowerName = "";

  private String assiBorrowerCardNum = "";

  private String orgId = "";

  private String empId = "";

  private String startDate = "";

  private String endDate = "";

  private String begstop = "";

  private String endstop = "";

  private String loanBankId = "";

  private String count = "";

  private String personCount = "";

  private String sta = "";

  private List list = new ArrayList();

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getAssiBorrowerCardNum() {
    return assiBorrowerCardNum;
  }

  public void setAssiBorrowerCardNum(String assiBorrowerCardNum) {
    this.assiBorrowerCardNum = assiBorrowerCardNum;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getXieYiNum() {
    return xieYiNum;
  }

  public void setXieYiNum(String xieYiNum) {
    this.xieYiNum = xieYiNum;
  }

  public String getAssiBorrowerName() {
    return assiBorrowerName;
  }

  public void setAssiBorrowerName(String assiBorrowerName) {
    this.assiBorrowerName = assiBorrowerName;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getPersonCount() {
    return personCount;
  }

  public void setPersonCount(String personCount) {
    this.personCount = personCount;
  }

  public String getSta() {
    return sta;
  }

  public void setSta(String sta) {
    this.sta = sta;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getBegstop() {
    return begstop;
  }

  public void setBegstop(String begstop) {
    this.begstop = begstop;
  }

  public String getEndstop() {
    return endstop;
  }

  public void setEndstop(String endstop) {
    this.endstop = endstop;
  }
}
