package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

public class OrgInfoDTO {

  private String unitName="";
  
  private String docNumber="";
  
  private String paymentHeadId="";

  public String getPaymentHeadId() {
    return paymentHeadId;
  }

  public void setPaymentHeadId(String paymentHeadId) {
    this.paymentHeadId = paymentHeadId;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }
}
