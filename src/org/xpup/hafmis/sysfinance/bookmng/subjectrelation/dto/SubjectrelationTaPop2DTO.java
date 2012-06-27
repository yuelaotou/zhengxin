package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTaPop2DTO {
  /*
   * 银行主键
   */
  private String collBankId = "";

 
  /*
   * 办事处名称
   */
  private String name = "";

  /*
   * 银行名称
   */
  private String collBankName = "";

  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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
