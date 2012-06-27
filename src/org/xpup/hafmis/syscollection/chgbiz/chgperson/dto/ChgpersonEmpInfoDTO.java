package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了准备插入AA205的职工信息Action Goldsoft Project :
 * ChgpersonEmpInfoDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.18
 */
public class ChgpersonEmpInfoDTO {
  private String empName = "";

  private String cardKind = "";

  private String cardNum = "";

  private String birthday = "";

  private String sex = "";

  private String dept = "";

  private String tel = "";

  private String payStatus = "";

  private String mobileTel = "";

  private BigDecimal salaryBase = new BigDecimal(0.00);

  private BigDecimal monthLncome = new BigDecimal(0.00);

  private BigDecimal orgPay = new BigDecimal(0.00);

  private BigDecimal empPay = new BigDecimal(0.00);

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public String getMobileTel() {
    return mobileTel;
  }

  public void setMobileTel(String mobileTel) {
    this.mobileTel = mobileTel;
  }

  public BigDecimal getMonthLncome() {
    return monthLncome;
  }

  public void setMonthLncome(BigDecimal monthLncome) {
    this.monthLncome = monthLncome;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public BigDecimal getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

}
