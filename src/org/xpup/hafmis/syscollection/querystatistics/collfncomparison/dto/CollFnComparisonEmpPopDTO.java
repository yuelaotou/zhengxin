package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto;

import java.math.BigDecimal;

public class CollFnComparisonEmpPopDTO {
  private String empId = "";

  private String empName = "";

  private BigDecimal debit = new BigDecimal(0.00);

  private BigDecimal credit = new BigDecimal(0.00);

  private BigDecimal interest = new BigDecimal(0.00);
  
  private BigDecimal occur = new BigDecimal(0.00);

  private String direction = "";

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

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
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

  public BigDecimal getOccur() {
    return occur;
  }

  public void setOccur(BigDecimal occur) {
    this.occur = occur;
  }
}
