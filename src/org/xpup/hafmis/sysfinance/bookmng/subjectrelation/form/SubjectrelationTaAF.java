package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class SubjectrelationTaAF extends ActionForm {
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
  /*
   * 表示科目代码在fn111中是否存在
   */
  private String type="";

  /*
   * 列表List
   */
  List list = null;

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
