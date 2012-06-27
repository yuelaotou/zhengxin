/*职工编号、职工姓名、证件号码、原单位缴额、原职工缴额、新单位缴额（空）、新职工缴额（空）*/
package org.xpup.hafmis.syscollection.chgbiz.chgpay.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;


public class ChgpayListImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String empId;

  private String empName;

  private String cardNum;

  private String oldOrgPay;

  private String oldEmpPay;

  private String orgPay;

  private String empPay;
  private String salaryBase;

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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

  public String getOldEmpPay() {
    return oldEmpPay;
  }

  public void setOldEmpPay(String oldEmpPay) {
    this.oldEmpPay = oldEmpPay;
  }

  public String getOldOrgPay() {
    return oldOrgPay;
  }

  public void setOldOrgPay(String oldOrgPay) {
    this.oldOrgPay = oldOrgPay;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }






}
