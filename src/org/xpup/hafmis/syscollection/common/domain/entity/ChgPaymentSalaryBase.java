package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * @author 卢钢 2007-6-20
 */
public class ChgPaymentSalaryBase extends ChgPaymentHead {

  private static final long serialVersionUID = -3655195047700033988L;

  // 合计：调整人数oldSalaryBaseSum
  // 调整前工资基数oldSalaryBase;
  // 调整后工资基数salaryBase;
  // 单位缴额orgPaySum;
  // 职工缴额empPaySum;
  // 缴额合计totalAmount;
  // 调整前总缴额oldPaySum;
  // 调整后总缴额paySum
  // 原总工资基数oldSlarayBase
  // 新总工资基数salaryBaseSum
  // 比率percentagerate
  // 提交状态

  private int oldSalaryBaseSum;

  private Double olddSalaryBase;

  private Double salaryBase;

  private Double orgPaySum;

  private Double empPaySum;

  private Double totalAmount;

  private Double paySum;

  private Double salaryBaseSum;

  private String chgtype;

  private String wuhtChgStatus;

  private String percentagerate;

  private String temp_pick;

  private Double orgRate;

  private Double empRate;

  private String month;

  public String getChgtype() {
    return "工资基数调整";
  }

  public Double getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(Double empPaySum) {
    this.empPaySum = empPaySum;
  }

  public Double getOlddSalaryBase() {
    return olddSalaryBase;
  }

  public void setOlddSalaryBase(Double olddSalaryBase) {
    this.olddSalaryBase = olddSalaryBase;
  }

  public int getOldSalaryBaseSum() {
    return oldSalaryBaseSum;
  }

  public void setOldSalaryBaseSum(int oldSalaryBaseSum) {
    this.oldSalaryBaseSum = oldSalaryBaseSum;
  }

  public Double getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(Double orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public Double getPaySum() {
    return paySum;
  }

  public void setPaySum(Double paySum) {
    this.paySum = paySum;
  }

  public Double getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(Double salaryBase) {
    this.salaryBase = salaryBase;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getWuhtChgStatus() {
    return wuhtChgStatus;
  }

  public void setWuhtChgStatus(String wuhtChgStatus) {
    this.wuhtChgStatus = wuhtChgStatus;
  }

  public Double getSalaryBaseSum() {
    return salaryBaseSum;
  }

  public void setSalaryBaseSum(Double salaryBaseSum) {
    this.salaryBaseSum = salaryBaseSum;
  }

  public String getPercentagerate() {
    return percentagerate;
  }

  public void setPercentagerate(String percentagerate) {
    this.percentagerate = percentagerate;
  }

  public String getTemp_pick() {
    return temp_pick;
  }

  public void setTemp_pick(String temp_pick) {
    this.temp_pick = temp_pick;
  }

  public Double getEmpRate() {
    return empRate;
  }

  public void setEmpRate(Double empRate) {
    this.empRate = empRate;
  }

  public Double getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(Double orgRate) {
    this.orgRate = orgRate;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

}
