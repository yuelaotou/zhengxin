package org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto;

public class CredencemodleDTO {
  /*
   * 科目代码
   */
  private String subjectCode = "";

  /*
   * 科目名称
   */
  private String subjectName = "";
  
  /*
   * 科目方向
   */
  private String subjectDirection = "";

  /*
   * 业务类型
   */
  private String bizType = "";

  /*
   * 金额类型
   */
  private String moneyType = "";

  /*
   * 摘要
   */
  private String summray = "";

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

  public String getSubjectDirection() {
    return subjectDirection;
  }

  public void setSubjectDirection(String subjectDirection) {
    this.subjectDirection = subjectDirection;
  }

  public String getSummray() {
    return summray;
  }

  public void setSummray(String summray) {
    this.summray = summray;
  }
}
