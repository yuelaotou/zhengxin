package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import java.io.Serializable;

public class EmpaddpayHeadPrintDTO implements Serializable{


  
  
  private static final long serialVersionUID = 1L;
  private String orgId="";
  private String orgName="";
  private String payMonth="";
  private String docNum="";
  private String personCounts="";
  private String pay = "";
  
  public String getPay() {
    return pay;
  }
  public void setPay(String pay) {
    this.pay = pay;
  }

  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
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
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public String getPersonCounts() {
    return personCounts;
  }
  public void setPersonCounts(String personCounts) {
    this.personCounts = personCounts;
  }
 

}
