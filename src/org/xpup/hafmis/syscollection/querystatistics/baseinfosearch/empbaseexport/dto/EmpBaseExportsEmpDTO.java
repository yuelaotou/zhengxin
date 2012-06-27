package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class EmpBaseExportsEmpDTO implements ExpDto{
  private String empId;

  private String empName;

  private String cardNum;

  private String salaryBase;

  private String orgPay;

  private String empPay;

  private String allPay;

  private String payStatus;

  private String org_pay_month;

  private String emp_pay_month;

  private String balance;

  public String getInfo(String info) {
    if (info.equals("empId")) {
      return this.empId;
    }
    if (info.equals("empName")) {
      return this.empName;
    }
    if (info.equals("cardNum")) {
      return this.cardNum;
    }
    if (info.equals("salaryBase")) {
      return this.salaryBase;
    }
    if (info.equals("orgPay")) {
      return this.orgPay;
    }
    if (info.equals("empPay")) {
      return this.empPay;
    }
    if (info.equals("allPay")) {
      return this.allPay;
    }
    if (info.equals("payStatus")) {
      return this.payStatus;
    }
    if (info.equals("org_pay_month")) {
      return this.org_pay_month;
    }
    if (info.equals("emp_pay_month")) {
      return this.emp_pay_month;
    }
    if (info.equals("balance")) {
      return this.balance;
    }
    return null;
  }

  public String getAllPay() {
    return allPay;
  }

  public void setAllPay(String allPay) {
    this.allPay = allPay;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmp_pay_month() {
    return emp_pay_month;
  }

  public void setEmp_pay_month(String emp_pay_month) {
    this.emp_pay_month = emp_pay_month;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getOrg_pay_month() {
    return org_pay_month;
  }

  public void setOrg_pay_month(String org_pay_month) {
    this.org_pay_month = org_pay_month;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }
}
