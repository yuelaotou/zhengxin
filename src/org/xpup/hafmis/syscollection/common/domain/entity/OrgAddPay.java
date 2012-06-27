package org.xpup.hafmis.syscollection.common.domain.entity;




/**
 * 缴存-单位补缴B
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgAddPay extends PaymentHead{
  
  private static final long serialVersionUID = -3745806964079944938L;
  private Integer payModel = new Integer(0);
  /** persistent field */
  private String payType;
  private String minMaxMonth="";

  public String getMinMaxMonth() {
    return minMaxMonth;
  }

  public void setMinMaxMonth(String minMaxMonth) {
    this.minMaxMonth = minMaxMonth;
  }
  public String getPayType() {
    return "单位补缴";
  }

  public Integer getPayModel() {
    return payModel;
  }

  public void setPayModel(Integer payModel) {
    this.payModel = payModel;
  }
  
}