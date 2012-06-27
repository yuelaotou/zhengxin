package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * 
 * @author 卢钢
 *2007-6-20
 */
public class ChgPaymentPayment extends ChgPaymentHead{

  private static final long serialVersionUID = 4620110895483067799L;
  //合计：调整人数oldPaymenSum
  //调整前单位缴额oldOrgPaySum;
  //调整后单位缴额oldOrgPaySum;
  //调整前职工缴额orgPaySum;
  //调整前职工缴额oldEmpPaySum;
  //调整后职工缴额empPaySum;
  //调整前总缴额oldPayment;
  //调整后总缴额paySum
  //比率percentagerate
  
  private String chgtype;
  
  private int oldPaymenSum;

  private Double oldOrgPaySum;
  
  private Double orgPaySum;
  private Double oldEmpPaySum;
  private Double empPaySum;
  private Double paySum;
  private String wuhtChgStatus;
  private String percentagerate;
  private String temp_pick;

  
  public String getTemp_pick() {
    return temp_pick;
  }

  public void setTemp_pick(String temp_pick) {
    this.temp_pick = temp_pick;
  }  
  
  public String getWuhtChgStatus() {
    return wuhtChgStatus;
  }

  public void setWuhtChgStatus(String wuhtChgStatus) {
    this.wuhtChgStatus = wuhtChgStatus;
  }

  public String getChgtype()
  {
    return "缴额调整";
  }

  public int getOldPaymenSum() {
    return oldPaymenSum;
  }

  public void setOldPaymenSum(int oldPaymenSum) {
    this.oldPaymenSum = oldPaymenSum;
  }

  

  public Double getOldEmpPaySum() {
    return oldEmpPaySum;
  }

  public void setOldEmpPaySum(Double oldEmpPaySum) {
    this.oldEmpPaySum = oldEmpPaySum;
  }

  public Double getOldOrgPaySum() {
    return oldOrgPaySum;
  }

  public void setOldOrgPaySum(Double oldOrgPaySum) {
    this.oldOrgPaySum = oldOrgPaySum;
  }

  public Double getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(Double empPaySum) {
    this.empPaySum = empPaySum;
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

  public String getPercentagerate() {
    return percentagerate;
  }

  public void setPercentagerate(String percentagerate) {
    this.percentagerate = percentagerate;
  }

}
