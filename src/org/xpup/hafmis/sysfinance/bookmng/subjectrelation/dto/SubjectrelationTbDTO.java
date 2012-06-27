package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTbDTO {
  /*
   * 科目代码
   */
  private String subjectCode = "";

  /*
   * 科目名称
   */
  private String subjectName = "";

  /*
   * 一级科目
   */
  private String firstSubjectCode = "";

  /*
   * 核算类别
   */
  private String calculRelaType = "";

  /*
   * 核算关系值
   */
  private String calculRelaValue = "";

  /*
   * 核算关系值名称
   */
  private String calculRelaName = "";

  /*
   * fn111 主键
   */
  private String subjectreleid = "";

  public String getCalculRelaName() {
    return calculRelaName;
  }

  public void setCalculRelaName(String calculRelaName) {
    this.calculRelaName = calculRelaName;
  }

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

  public String getSubjectreleid() {
    return subjectreleid;
  }

  public void setSubjectreleid(String subjectreleid) {
    this.subjectreleid = subjectreleid;
  }
}
