package org.xpup.hafmis.syscollection.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author 王玲
 * 2007-6-26
 * 该方法用于返回计算年终利息（本年利息、往年利息）
 */
public class InterestDto implements Serializable{

  private static final long serialVersionUID = 0L;
  
  private BigDecimal  curInterest = new BigDecimal(0.00);//本年利息
  private BigDecimal  preInterest = new BigDecimal(0.00);//往年利息
  
  public BigDecimal getCurInterest() {
    return curInterest;
  }
  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
  }
  public BigDecimal getPreInterest() {
    return preInterest;
  }
  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

}
