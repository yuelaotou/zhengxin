package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.dto;

public class BusinesslogsearchDTO {
  
  private String bizId="";
  
  private String bizType="";
  private String bizType_temp="";
  
  private String payStatus="";
  
  private String operatorTime="";
  
  private String operator="";

  public String getBizId() {
    return bizId;
  }

  public void setBizId(String bizId) {
    this.bizId = bizId;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getOperatorTime() {
    return operatorTime;
  }

  public void setOperatorTime(String operatorTime) {
    this.operatorTime = operatorTime;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getBizType_temp() {
    return bizType_temp;
  }

  public void setBizType_temp(String bizType_temp) {
    this.bizType_temp = bizType_temp;
  }

}
