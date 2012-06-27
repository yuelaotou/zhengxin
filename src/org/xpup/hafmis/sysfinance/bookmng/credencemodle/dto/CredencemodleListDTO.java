package org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto;

public class CredencemodleListDTO {
  /*
   * 科目代码
   */
  private String subjectCode = "";

  /*
   * 科目名称
   */
  private String subjectName = "";

  /*
   * 业务类型
   */
  private String bizType = "";

  /*
   * 金额类型
   */
  private String moneyType = "";

  /*
   * 金额类型
   */
  private String balanceDirection = "";

  /*
   * fn120主键
   */
  private String id = "";

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getMoneyType() {
    return moneyType;
  }

  public void setMoneyType(String moneyType) {
    this.moneyType = moneyType;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }
}
