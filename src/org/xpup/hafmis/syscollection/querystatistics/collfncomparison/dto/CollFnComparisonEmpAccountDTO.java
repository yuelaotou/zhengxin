package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto;

import java.math.BigDecimal;

public class CollFnComparisonEmpAccountDTO {

  private String collDocNum = "";

  private String fnDocNum = "";

  private String settNum = "";

  private String bizType = "";

  private String collDate = "";

  private String bizStatus = "";

  private String caiwStatus = "";//财务里的状态
  
  private String fnDate = "";

  private String credenceSt = "";

  private String type = "";

  private BigDecimal debit = new BigDecimal(0.00);

  private BigDecimal credit = new BigDecimal(0.00);

  private BigDecimal remnant = new BigDecimal(0.00);
  
  private BigDecimal moneySum = new BigDecimal(0.00);

  /**
   * 销户利息
   */
  private BigDecimal interest = new BigDecimal(0.00);
  /**
   * 方向
   */
  private String direction = "";

  private String orgId = "";
  private String orgName = "";
  private String empId = "";
  private String empName = "";
  
  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(String bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getCollDate() {
    return collDate;
  }

  public void setCollDate(String collDate) {
    this.collDate = collDate;
  }

  public String getCollDocNum() {
    return collDocNum;
  }

  public void setCollDocNum(String collDocNum) {
    this.collDocNum = collDocNum;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getFnDate() {
    return fnDate;
  }

  public void setFnDate(String fnDate) {
    this.fnDate = fnDate;
  }

  public String getFnDocNum() {
    return fnDocNum;
  }

  public void setFnDocNum(String fnDocNum) {
    this.fnDocNum = fnDocNum;
  }

  public BigDecimal getRemnant() {
    return remnant;
  }

  public void setRemnant(BigDecimal remnant) {
    this.remnant = remnant;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getMoneySum() {
    return moneySum;
  }

  public void setMoneySum(BigDecimal moneySum) {
    this.moneySum = moneySum;
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

  public String getCaiwStatus() {
    return caiwStatus;
  }

  public void setCaiwStatus(String caiwStatus) {
    this.caiwStatus = caiwStatus;
  }
}
