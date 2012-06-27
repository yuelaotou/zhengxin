package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto;

import java.math.BigDecimal;

public class CollFnComparisonEmpInfoDTO {

  private Integer empid;

  private String empname;

  private String cardnum;

  private BigDecimal salarybase;

  private BigDecimal orgpay;

  private BigDecimal emppay;

  private BigDecimal paycount;

  private String paystatus;

  private String orgpaymonth;

  private String emppaymonth;

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public Integer getEmpid() {
    return empid;
  }

  public void setEmpid(Integer empid) {
    this.empid = empid;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public String getEmppaymonth() {
    return emppaymonth;
  }

  public void setEmppaymonth(String emppaymonth) {
    this.emppaymonth = emppaymonth;
  }

  public String getOrgpaymonth() {
    return orgpaymonth;
  }

  public void setOrgpaymonth(String orgpaymonth) {
    this.orgpaymonth = orgpaymonth;
  }

  public String getPaystatus() {
    return paystatus;
  }

  public void setPaystatus(String paystatus) {
    this.paystatus = paystatus;
  }

  public BigDecimal getEmppay() {
    return emppay;
  }

  public void setEmppay(BigDecimal emppay) {
    this.emppay = emppay;
  }

  public BigDecimal getOrgpay() {
    return orgpay;
  }

  public void setOrgpay(BigDecimal orgpay) {
    this.orgpay = orgpay;
  }

  public BigDecimal getPaycount() {
    return paycount;
  }

  public void setPaycount(BigDecimal paycount) {
    this.paycount = paycount;
  }

  public BigDecimal getSalarybase() {
    return salarybase;
  }

  public void setSalarybase(BigDecimal salarybase) {
    this.salarybase = salarybase;
  }

}
