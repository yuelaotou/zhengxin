package org.xpup.hafmis.signjoint.dto;

public class LogDTO {

  private String id;
  private String file_name;
  private String operation_type;
  private String op_date;
  public LogDTO() {
    this.id = "";
    this.file_name = "";
    this.operation_type = "";
    this.op_date = "";
  }
  public LogDTO(String id, String file_name, String operation_type, String op_date) {
    this.id = id;
    this.file_name = file_name;
    this.operation_type = operation_type;
    this.op_date = op_date;
  }
  public String getFile_name() {
    return file_name;
  }
  public void setFile_name(String file_name) {
    this.file_name = file_name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOp_date() {
    return op_date;
  }
  public void setOp_date(String op_date) {
    this.op_date = op_date;
  }
  public String getOperation_type() {
    return operation_type;
  }
  public void setOperation_type(String operation_type) {
    this.operation_type = operation_type;
  }

}
