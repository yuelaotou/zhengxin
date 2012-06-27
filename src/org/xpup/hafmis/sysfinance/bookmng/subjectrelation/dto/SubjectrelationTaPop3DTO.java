package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTaPop3DTO {
  /*
   * 单位主键
   */
  private String id = "";

  /*
   * 办事处名称
   */
  private String name = "";

  /*
   * 银行名称
   */
  private String bankName = "";

  /*
   * 单位名称
   */
  private String orgName = "";

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
