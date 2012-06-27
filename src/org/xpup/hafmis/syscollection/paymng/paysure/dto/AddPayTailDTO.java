package org.xpup.hafmis.syscollection.paymng.paysure.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddPayTailDTO implements Serializable {
  private static final long serialVersionUID = 7824999389240436586L;
  
  private Integer empid = new Integer(0);
  private BigDecimal sumPaymoney = new BigDecimal(0);
  
  public Integer getEmpid() {
    return empid;
  }
  public void setEmpid(Integer empid) {
    this.empid = empid;
  }
  public BigDecimal getSumPaymoney() {
    return sumPaymoney;
  }
  public void setSumPaymoney(BigDecimal sumPaymoney) {
    this.sumPaymoney = sumPaymoney;
  }
  
}
