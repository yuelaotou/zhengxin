package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto;

public class PastyearscontrasDTO {

  private String orgOpen = "";//单位开户
  private String empOpen = "";//职工开户
  private String payCount = "";//缴额调整笔数
  private String payChg = "";//缴额变化
  private String rateCount = "";//汇缴比例调整笔数
  private String rateChg = "";//汇缴比例调整变化
  private String laborageCount = "";//工资笔数
  private String laborageChg = "";//工资缴额变化
  private String empCount = "";//人员变更笔数
  private String empChg = "";//人员变更缴额变化
  private String sumCount = "";//笔数合计
  private String sumChg = "";//缴额变化合计
  
  public String getEmpChg() {
    return empChg;
  }
  public void setEmpChg(String empChg) {
    this.empChg = empChg;
  }
  public String getEmpCount() {
    return empCount;
  }
  public void setEmpCount(String empCount) {
    this.empCount = empCount;
  }
  public String getEmpOpen() {
    return empOpen;
  }
  public void setEmpOpen(String empOpen) {
    this.empOpen = empOpen;
  }
  public String getLaborageChg() {
    return laborageChg;
  }
  public void setLaborageChg(String laborageChg) {
    this.laborageChg = laborageChg;
  }
  public String getLaborageCount() {
    return laborageCount;
  }
  public void setLaborageCount(String laborageCount) {
    this.laborageCount = laborageCount;
  }
  public String getOrgOpen() {
    return orgOpen;
  }
  public void setOrgOpen(String orgOpen) {
    this.orgOpen = orgOpen;
  }
  public String getPayChg() {
    return payChg;
  }
  public void setPayChg(String payChg) {
    this.payChg = payChg;
  }
  public String getPayCount() {
    return payCount;
  }
  public void setPayCount(String payCount) {
    this.payCount = payCount;
  }
  public String getRateChg() {
    return rateChg;
  }
  public void setRateChg(String rateChg) {
    this.rateChg = rateChg;
  }
  public String getRateCount() {
    return rateCount;
  }
  public void setRateCount(String rateCount) {
    this.rateCount = rateCount;
  }
  public String getSumChg() {
    return sumChg;
  }
  public void setSumChg(String sumChg) {
    this.sumChg = sumChg;
  }
  public String getSumCount() {
    return sumCount;
  }
  public void setSumCount(String sumCount) {
    this.sumCount = sumCount;
  }
  
  
}
