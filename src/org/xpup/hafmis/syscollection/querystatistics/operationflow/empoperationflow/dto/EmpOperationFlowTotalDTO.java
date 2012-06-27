package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto;

public class EmpOperationFlowTotalDTO{
  
  private String counts = "";
  
  private String sumMoney = "";
  
  private String sumInterest = "";

  public String getCounts() {
    return counts;
  }

  public void setCounts(String counts) {
    this.counts = counts;
  }

  public String getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(String sumInterest) {
    this.sumInterest = sumInterest;
  }

  public String getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(String sumMoney) {
    this.sumMoney = sumMoney;
  }
  
  
}