package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;

public class CollBankDTO implements Serializable{

  private static final long serialVersionUID = -5885105239139063148L;
  private String collBankId="";
  private String collBankName="";
  public String getCollBankId() {
    return collBankId;
  }
  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }
  public String getCollBankName() {
    return collBankName;
  }
  public void setCollBankName(String collBankName) {
    this.collBankName = collBankName;
  }
  

}
