package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class TraninExportDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

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

  public String getInfo(String s) {
    if (s.equals("inOrgId"))
      return inOrgId;
    if (s.equals("inOrgName"))
      return inOrgName;
    if (s.equals("noteNum"))
      return noteNum;
    if (s.equals("name"))
      return name;
    if (s.equals("cardKind"))
      return cardKind;
    if (s.equals("cardNum"))
      return cardNum;
    if (s.equals("birthday"))
      return birthday;
    if (s.equals("sex"))
      return sex;
    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("preBalance"))
      return preBalance;
    if (s.equals("curBalance"))
      return curBalance;
    if (s.equals("monthIncome"))
      return monthIncome;
    if (s.equals("curInterest"))
      return curInterest;
    if (s.equals("tel"))
      return tel;
    if (s.equals("mobileTel"))
      return mobileTel;
    if (s.equals("orgPay"))
      return orgPay;
    if (s.equals("empPay"))
      return empPay;
    else
      return null;
  }

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

  public String getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(String preBalance) {
    this.preBalance = preBalance;
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

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getMobileTel() {
    return mobileTel;
  }

  public void setMobileTel(String mobileTel) {
    this.mobileTel = mobileTel;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getMonthIncome() {
    return monthIncome;
  }

  public void setMonthIncome(String monthIncome) {
    this.monthIncome = monthIncome;
  }

}
