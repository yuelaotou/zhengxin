package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;

public class TraninDTO implements Serializable {

  private static final long serialVersionUID = 0L;

  private TranInHead tranInHead = new TranInHead();

  /** nullable persistent field */
  private Emp emp = new Emp();

  /** nullable persistent field */
  private String name;

  /** nullable persistent field */
  private Integer cardKind;

  /** nullable persistent field */
  private String cardNum;

  /** nullable persistent field */
  private Date birthday;

  /** nullable persistent field */
  private Integer sex;

  /** nullable persistent field */
  private String dept;

  /** nullable persistent field */
  private String tel;

  /** nullable persistent field */
  private String mobileTel;

  /** nullable persistent field */
  private BigDecimal salaryBase;

  /** nullable persistent field */
  private BigDecimal monthIncome;

  /** nullable persistent field */
  private BigDecimal orgPay;

  /** nullable persistent field */
  private BigDecimal empPay;

  /** nullable persistent field */
  private String openDate;

  /** nullable persistent field */
  private BigDecimal preBalance;

  /** nullable persistent field */
  private BigDecimal curBalance;

  /** nullable persistent field */
  private BigDecimal preInterest;

  /** nullable persistent field */
  private BigDecimal curInterest;

  /** nullable persistent field */
  private BigDecimal preIntegral;

  /** nullable persistent field */
  private BigDecimal curIntegral;

  private BigDecimal mergerEmpid;

  /** 分段往年积数a */
  private BigDecimal preIntegralSubA;

  private BigDecimal curIntegralSubA;

  /** 往年利率a */
  private BigDecimal preRateA;

  private BigDecimal curRateA;

  /** 分段往年积数b */
  private BigDecimal preIntegralSubB;

  private BigDecimal curIntegralSubB;

  private BigDecimal preRateB;

  private BigDecimal curRateB;

  private BigDecimal preIntegralSubC;

  private BigDecimal curIntegralSubC;

  private BigDecimal preRateC;

  private BigDecimal curRateC;

  /** 备选a */
  private String reserveaA;

  private String reserveaB;

  private String reserveaC;

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getCardKind() {
    return cardKind;
  }

  public void setCardKind(Integer cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BigDecimal getCurBalance() {
    return curBalance;
  }

  public void setCurBalance(BigDecimal curBalance) {
    this.curBalance = curBalance;
  }

  public BigDecimal getCurIntegral() {
    return curIntegral;
  }

  public void setCurIntegral(BigDecimal curIntegral) {
    this.curIntegral = curIntegral;
  }

  public BigDecimal getCurIntegralSubA() {
    return curIntegralSubA;
  }

  public void setCurIntegralSubA(BigDecimal curIntegralSubA) {
    this.curIntegralSubA = curIntegralSubA;
  }

  public BigDecimal getCurIntegralSubB() {
    return curIntegralSubB;
  }

  public void setCurIntegralSubB(BigDecimal curIntegralSubB) {
    this.curIntegralSubB = curIntegralSubB;
  }

  public BigDecimal getCurIntegralSubC() {
    return curIntegralSubC;
  }

  public void setCurIntegralSubC(BigDecimal curIntegralSubC) {
    this.curIntegralSubC = curIntegralSubC;
  }

  public BigDecimal getCurInterest() {
    return curInterest;
  }

  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
  }

  public BigDecimal getCurRateA() {
    return curRateA;
  }

  public void setCurRateA(BigDecimal curRateA) {
    this.curRateA = curRateA;
  }

  public BigDecimal getCurRateB() {
    return curRateB;
  }

  public void setCurRateB(BigDecimal curRateB) {
    this.curRateB = curRateB;
  }

  public BigDecimal getCurRateC() {
    return curRateC;
  }

  public void setCurRateC(BigDecimal curRateC) {
    this.curRateC = curRateC;
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public Emp getEmp() {
    return emp;
  }

  public void setEmp(Emp emp) {
    this.emp = emp;
  }

  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public BigDecimal getMergerEmpid() {
    return mergerEmpid;
  }

  public void setMergerEmpid(BigDecimal mergerEmpid) {
    this.mergerEmpid = mergerEmpid;
  }

  public String getMobileTel() {
    return mobileTel;
  }

  public void setMobileTel(String mobileTel) {
    this.mobileTel = mobileTel;
  }

  public BigDecimal getMonthIncome() {
    return monthIncome;
  }

  public void setMonthIncome(BigDecimal monthIncome) {
    this.monthIncome = monthIncome;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOpenDate() {
    return openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public BigDecimal getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(BigDecimal preBalance) {
    this.preBalance = preBalance;
  }

  public BigDecimal getPreIntegral() {
    return preIntegral;
  }

  public void setPreIntegral(BigDecimal preIntegral) {
    this.preIntegral = preIntegral;
  }

  public BigDecimal getPreIntegralSubA() {
    return preIntegralSubA;
  }

  public void setPreIntegralSubA(BigDecimal preIntegralSubA) {
    this.preIntegralSubA = preIntegralSubA;
  }

  public BigDecimal getPreIntegralSubB() {
    return preIntegralSubB;
  }

  public void setPreIntegralSubB(BigDecimal preIntegralSubB) {
    this.preIntegralSubB = preIntegralSubB;
  }

  public BigDecimal getPreIntegralSubC() {
    return preIntegralSubC;
  }

  public void setPreIntegralSubC(BigDecimal preIntegralSubC) {
    this.preIntegralSubC = preIntegralSubC;
  }

  public BigDecimal getPreInterest() {
    return preInterest;
  }

  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

  public BigDecimal getPreRateA() {
    return preRateA;
  }

  public void setPreRateA(BigDecimal preRateA) {
    this.preRateA = preRateA;
  }

  public BigDecimal getPreRateB() {
    return preRateB;
  }

  public void setPreRateB(BigDecimal preRateB) {
    this.preRateB = preRateB;
  }

  public BigDecimal getPreRateC() {
    return preRateC;
  }

  public void setPreRateC(BigDecimal preRateC) {
    this.preRateC = preRateC;
  }

  public String getReserveaA() {
    return reserveaA;
  }

  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }

  public String getReserveaB() {
    return reserveaB;
  }

  public void setReserveaB(String reserveaB) {
    this.reserveaB = reserveaB;
  }

  public String getReserveaC() {
    return reserveaC;
  }

  public void setReserveaC(String reserveaC) {
    this.reserveaC = reserveaC;
  }

  public BigDecimal getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public TranInHead getTranInHead() {
    return tranInHead;
  }

  public void setTranInHead(TranInHead tranInHead) {
    this.tranInHead = tranInHead;
  }

}
