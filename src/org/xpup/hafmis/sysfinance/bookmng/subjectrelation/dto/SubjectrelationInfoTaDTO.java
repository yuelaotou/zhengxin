package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

import java.math.BigDecimal;

public class SubjectrelationInfoTaDTO {
  /*
   * 科目代码
   */
  private String subjectCode = "";

  /*
   * 科目名称
   */
  private String subjectName = "";

  /*
   * 发生额方向
   */
  private String balanceDirection = "";

  /*
   * 科目余额
   */
  private BigDecimal balance = new BigDecimal(0.00);

  /*
   * 一级科目
   */
  private String firstSubjectCode = "";

  /*
   * 核算类别
   */
  private String calculRelaType = "";

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }

  public String getCalculRelaType() {
    return calculRelaType;
  }

  public void setCalculRelaType(String calculRelaType) {
    this.calculRelaType = calculRelaType;
  }

  public String getFirstSubjectCode() {
    return firstSubjectCode;
  }

  public void setFirstSubjectCode(String firstSubjectCode) {
    this.firstSubjectCode = firstSubjectCode;
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

}
