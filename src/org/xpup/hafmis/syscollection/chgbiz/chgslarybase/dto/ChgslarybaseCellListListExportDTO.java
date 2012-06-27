/*职工编号 ▲  职工姓名  证件号码  调整前工资基数  调整前单位缴额  调整前职工缴额  调整前缴额合计  调整后工资基数  调整后单位缴额  调整后职工缴额  调整后缴额合计 、*/
 //吴洪涛2008616
package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgslarybaseCellListListExportDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

  private String empId;// 职工编号

  private String empName;// 职工姓名

  private String cardNum;// 证件号码

  private String oldSalaryBase;// 调整前工资基数

  private String oldOrgPay;// 调整前单位缴额

  private String oldEmpPay;// 调整前职工缴额

  private String oldOrgPayEmpPaySum;// 调整前缴额合计

  private String salaryBase;// 调整后工资基数

  private String orgPay;// 调整后单位缴额

  private String empPay;// 调整后职工缴额

  private String oldPaySum;// 调整后缴额合计
  
  private String orgId;

  private String orgName;

  private String chgMonth;

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
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

  public String getOldOrgPayEmpPaySum() {
    return oldOrgPayEmpPaySum;
  }

  public void setOldOrgPayEmpPaySum(String oldOrgPayEmpPaySum) {
    this.oldOrgPayEmpPaySum = oldOrgPayEmpPaySum;
  }

  public String getOldPaySum() {
    return oldPaySum;
  }

  public void setOldPaySum(String oldPaySum) {
    this.oldPaySum = oldPaySum;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
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

  public String getOldSalaryBase() {
    return oldSalaryBase;
  }

  public void setOldSalaryBase(String oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getInfo(String s) {
    if (s.equals("empId"))
      return empId;
    if (s.equals("empName"))
      return empName;
    if (s.equals("cardNum"))
      return cardNum;
    
    if (s.equals("oldSalaryBase"))
      return oldSalaryBase;
    if (s.equals("oldOrgPay"))
      return oldOrgPay;
    if (s.equals("oldEmpPay"))
      return oldEmpPay;
    if (s.equals("oldOrgPayEmpPaySum"))
      return oldOrgPayEmpPaySum;
    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("orgPay"))
      return orgPay;
    if (s.equals("empPay"))
      return empPay;
    if (s.equals("oldPaySum"))
      return oldPaySum;
    else
      return null;
  }

}
