package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class TraninImportDTO extends impDto {
  /**
   * 
   */
  private static final long serialVersionUID = -7458394355768841510L;

  private String inOrgId;

  private String inOrgName;

  private String noteNum;

  private String name = "";

  private String cardKind = "";

  private String cardNum = "";

  private String birthday = "";

  private String sex = "";

  private String salaryBase = "";

  private String preBalance = "";

  private String curBalance = "";

  private String monthIncome = "";

  private String curInterest = "";

  private String tel = "";

  private String mobileTel = "";

  private String orgPay = "";

  private String empPay = "";

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

  public String getCurBalance() {
    return curBalance;
  }

  public void setCurBalance(String curBalance) {
    this.curBalance = curBalance;
  }

  public String getCurInterest() {
    return curInterest;
  }

  public void setCurInterest(String curInterest) {
    this.curInterest = curInterest;
  }

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getInOrgName() {
    return inOrgName;
  }

  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }

  public String getMobileTel() {
    return mobileTel;
  }

  public void setMobileTel(String mobileTel) {
    this.mobileTel = mobileTel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }

  public String getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(String preBalance) {
    this.preBalance = preBalance;
  }

  public String getMonthIncome() {
    return monthIncome;
  }

  public void setMonthIncome(String monthIncome) {
    this.monthIncome = monthIncome;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
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

}
