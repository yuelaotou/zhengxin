package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import java.math.BigDecimal;
public class PersonAddPayDTO  {
  
  private String empId="";
  
  private String empName="";
  
  private BigDecimal unitAddPayAmount=new BigDecimal(0.00);
  
  private BigDecimal personAddPayAmount=new BigDecimal(0.00);
  
  private BigDecimal addPayAmount=new BigDecimal(0.00);
  
  private String addPayBeginYearMonth="";
  
  private String addPayEndYearMonth="";
  
  private String addPayReason="";
  
  private String personSum="";
  
  private BigDecimal orgPaySum=new BigDecimal(0.00);
  
  private BigDecimal empPaySum=new BigDecimal(0.00);
  
  private BigDecimal addPaySum=new BigDecimal(0.00);
  
  private String orgId="";
  
  private String orgName="";
  
  private String docNum="";
  
  private String id="";

  private String sex="";

  private String tempPickType="";
  
  private String cardNum = "";//身份证号
  
  private String payMode = "";//补缴类型
  
  private BigDecimal salaryBase = new BigDecimal(0.00);//工资基数
  
  private BigDecimal orgRate = new BigDecimal(0.00);//单位汇缴比例 
  
  private String monthCounts = "";//补缴月数
  
  public String getMonthCounts() {
    return monthCounts;
  }

  public void setMonthCounts(String monthCounts) {
    this.monthCounts = monthCounts;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
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

  public BigDecimal getAddPayAmount() {
    return addPayAmount;
  }

  public void setAddPayAmount(BigDecimal addPayAmount) {
    this.addPayAmount = addPayAmount;
  }

  public String getAddPayBeginYearMonth() {
    return addPayBeginYearMonth;
  }

  public void setAddPayBeginYearMonth(String addPayBeginYearMonth) {
    this.addPayBeginYearMonth = addPayBeginYearMonth;
  }

  public String getAddPayEndYearMonth() {
    return addPayEndYearMonth;
  }

  public void setAddPayEndYearMonth(String addPayEndYearMonth) {
    this.addPayEndYearMonth = addPayEndYearMonth;
  }

  public String getAddPayReason() {
    return addPayReason;
  }

  public void setAddPayReason(String addPayReason) {
    this.addPayReason = addPayReason;
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

  public BigDecimal getPersonAddPayAmount() {
    return personAddPayAmount;
  }

  public void setPersonAddPayAmount(BigDecimal personAddPayAmount) {
    this.personAddPayAmount = personAddPayAmount;
  }

  public BigDecimal getUnitAddPayAmount() {
    return unitAddPayAmount;
  }

  public void setUnitAddPayAmount(BigDecimal unitAddPayAmount) {
    this.unitAddPayAmount = unitAddPayAmount;
  }

  public BigDecimal getAddPaySum() {
    return addPaySum;
  }

  public void setAddPaySum(BigDecimal addPaySum) {
    this.addPaySum = addPaySum;
  }

  public BigDecimal getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(BigDecimal empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(BigDecimal orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public String getPersonSum() {
    return personSum;
  }

  public void setPersonSum(String personSum) {
    this.personSum = personSum;
  }

  public String getTempPickType() {
    return tempPickType;
  }

  public void setTempPickType(String tempPickType) {
    this.tempPickType = tempPickType;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BigDecimal getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }

  public BigDecimal getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }
  
  

}
