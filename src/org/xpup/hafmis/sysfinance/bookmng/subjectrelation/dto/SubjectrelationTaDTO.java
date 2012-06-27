package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTaDTO {
  /*
   * 核算关系值
   */
  private String calculRelaValue = "";

  /*
   * 核算关系值名称
   */
  private String calculName = "";

  public String getCalculName() {
    return calculName;
  }

  public void setCalculName(String calculName) {
    this.calculName = calculName;
  }

  public String getCalculRelaValue() {
    return calculRelaValue;
  }

  public void set(String calculRelaValue) {
    this.calculRelaValue = calculRelaValue;
  }
}
