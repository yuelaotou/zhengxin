package org.xpup.hafmis.syscollection.collloanback.dto;

import java.math.BigDecimal;

public class CollLoanbackTaListDTO {
  private String id="";
  private String kouLoanAcc="";
  private String borrowerName="";
  private BigDecimal shouldCorpus=new BigDecimal(0.00);
  private String yearMonth ="";
  private String flag="";
  private String contractId="";
  
  private String orgid="";
  private String empid="";
  private String balance="";
  
  private String c_count="";
  private String p_count="";
  private String kouLoanAcc2="";
  private BigDecimal m_sum=new BigDecimal(0.00);
  
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getFlag() {
    return flag;
  }
  public void setFlag(String flag) {
    this.flag = flag;
  }
  public String getKouLoanAcc() {
    return kouLoanAcc;
  }
  public void setKouLoanAcc(String kouLoanAcc) {
    this.kouLoanAcc = kouLoanAcc;
  }
  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }
  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }
  public String getYearMonth() {
    return yearMonth;
  }
  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getC_count() {
    return c_count;
  }
  public void setC_count(String c_count) {
    this.c_count = c_count;
  }
  public BigDecimal getM_sum() {
    return m_sum;
  }
  public void setM_sum(BigDecimal m_sum) {
    this.m_sum = m_sum;
  }
  public String getP_count() {
    return p_count;
  }
  public void setP_count(String p_count) {
    this.p_count = p_count;
  }
  public String getKouLoanAcc2() {
    return kouLoanAcc2;
  }
  public void setKouLoanAcc2(String kouLoanAcc2) {
    this.kouLoanAcc2 = kouLoanAcc2;
  }
}
