package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class SubjectrelationTbAF extends ActionForm {
  /*
   * 科目代码
   */
  private String subjectCode = "";

  /*
   * 科目名称
   */
  private String subjectName = "";

  /*
   * 核算类别
   */
  private String calculRelaType = "";

  /*
   * 核算关系值
   */
  private String calculRelaValue = "";

  /*
   * 核算关系值单位
   */
  private String orgid = "";

  /*
   * 核算关系值办事处
   */
  private String office = "";

  /*
   * 核算关系值银行
   */
  private String bankid = "";

  List list = null;

  /*
   * 核算关系类型枚举
   */
  private Map calculRelaTypeMap = null;

  public String getCalculRelaType() {
    return calculRelaType;
  }

  public void setCalculRelaType(String calculRelaType) {
    this.calculRelaType = calculRelaType;
  }

  public String getCalculRelaValue() {
    return calculRelaValue;
  }

  public void setCalculRelaValue(String calculRelaValue) {
    this.calculRelaValue = calculRelaValue;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getCalculRelaTypeMap() {
    return calculRelaTypeMap;
  }

  public void setCalculRelaTypeMap(Map calculRelaTypeMap) {
    this.calculRelaTypeMap = calculRelaTypeMap;
  }

  public String getBankid() {
    return bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
}
