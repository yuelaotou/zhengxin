package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.dto;

public class SettleincanddecListDTO {
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
  private String bySubjectCode = "";

  /*
   * 金额类型
   */
  private String bySubjectName = "";

  /*
   * fn202主键
   */
  private String id = "";

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

  public String getBySubjectCode() {
    return bySubjectCode;
  }

  public void setBySubjectCode(String bySubjectCode) {
    this.bySubjectCode = bySubjectCode;
  }

  public String getBySubjectName() {
    return bySubjectName;
  }

  public void setBySubjectName(String bySubjectName) {
    this.bySubjectName = bySubjectName;
  }
}
