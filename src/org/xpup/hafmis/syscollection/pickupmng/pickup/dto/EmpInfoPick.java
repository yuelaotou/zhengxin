package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import java.math.BigDecimal;

public class EmpInfoPick {

  private String empId;
  private BigDecimal pre_balance=new BigDecimal(0.00); 
  private BigDecimal cur_balance=new BigDecimal(0.00);
  public BigDecimal getCur_balance() {
    return cur_balance;
  }
  public void setCur_balance(BigDecimal cur_balance) {
    this.cur_balance = cur_balance;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public BigDecimal getPre_balance() {
    return pre_balance;
  }
  public void setPre_balance(BigDecimal pre_balance) {
    this.pre_balance = pre_balance;
  } 
  
  
}
