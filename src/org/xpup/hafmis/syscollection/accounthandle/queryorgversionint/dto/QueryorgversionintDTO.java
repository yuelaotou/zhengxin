package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.dto;



import java.math.BigDecimal;

public class QueryorgversionintDTO {
  private String pid="";//主键
  private String empId = "";//职工编号
  private String empName = "";//职工姓名
  private String clearInterestType = "";//结息类型
  private BigDecimal money = new BigDecimal(0.00);//金额
  private BigDecimal interest = new BigDecimal(0.00);//利息
  private BigDecimal sumMoney = new BigDecimal(0.00);//总额
  public String getClearInterestType() {
    return clearInterestType;
  }
  public void setClearInterestType(String clearInterestType) {
    this.clearInterestType = clearInterestType;
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
  public BigDecimal getInterest() {
    return interest;
  }
  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  public String getPid() {
    return pid;
  }
  public void setPid(String pid) {
    this.pid = pid;
  }
  
}
