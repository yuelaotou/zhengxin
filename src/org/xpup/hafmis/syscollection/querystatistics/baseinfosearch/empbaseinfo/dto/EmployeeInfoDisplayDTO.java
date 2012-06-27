package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto;

import java.math.BigDecimal;

public class EmployeeInfoDisplayDTO {
  private Integer orgid;

  private Integer empid;

  private String empname;

  private String cardnum;

  private String salarybase;

  private String orgpay;

  private String emppay;

  private String paycount;

  private String paystatus;

  private String orgpaymonth;

  private String emppaymonth;

  private String balance;

  private Integer pickcount;

  private String pickmoney;
  
  private BigDecimal payOldYear = new BigDecimal(0.00);

  // 是否存在贷款 0是,1否
  private String loanYesNo = "";
  
  private BigDecimal maxPickMoney = new BigDecimal(0.00);

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public String getEmppay() {
    return emppay;
  }

  public void setEmppay(String emppay) {
    this.emppay = emppay;
  }

  public String getEmppaymonth() {
    return emppaymonth;
  }

  public void setEmppaymonth(String emppaymonth) {
    this.emppaymonth = emppaymonth;
  }

  public String getOrgpay() {
    return orgpay;
  }

  public void setOrgpay(String orgpay) {
    this.orgpay = orgpay;
  }

  public String getOrgpaymonth() {
    return orgpaymonth;
  }

  public void setOrgpaymonth(String orgpaymonth) {
    this.orgpaymonth = orgpaymonth;
  }

  public String getPaycount() {
    return paycount;
  }

  public void setPaycount(String paycount) {
    this.paycount = paycount;
  }

  public String getPaystatus() {
    return paystatus;
  }

  public void setPaystatus(String paystatus) {
    this.paystatus = paystatus;
  }

  public String getPickmoney() {
    return pickmoney;
  }

  public void setPickmoney(String pickmoney) {
    this.pickmoney = pickmoney;
  }

  public String getSalarybase() {
    return salarybase;
  }

  public void setSalarybase(String salarybase) {
    this.salarybase = salarybase;
  }

  public Integer getPickcount() {
    return pickcount;
  }

  public void setPickcount(Integer pickcount) {
    this.pickcount = pickcount;
  }

  public Integer getEmpid() {
    return empid;
  }

  public void setEmpid(Integer empid) {
    this.empid = empid;
  }

  public Integer getOrgid() {
    return orgid;
  }

  public void setOrgid(Integer orgid) {
    this.orgid = orgid;
  }

  public String getLoanYesNo() {
    return loanYesNo;
  }

  public void setLoanYesNo(String loanYesNo) {
    this.loanYesNo = loanYesNo;
  }

  public BigDecimal getMaxPickMoney() {
    return maxPickMoney;
  }

  public void setMaxPickMoney(BigDecimal maxPickMoney) {
    this.maxPickMoney = maxPickMoney;
  }

  public BigDecimal getPayOldYear() {
    return payOldYear;
  }

  public void setPayOldYear(BigDecimal payOldYear) {
    this.payOldYear = payOldYear;
  }
}
