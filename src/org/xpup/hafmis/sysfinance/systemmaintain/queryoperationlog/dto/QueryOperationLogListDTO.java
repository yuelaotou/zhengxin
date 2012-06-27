package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto;

/**
 * Copy Right Information : 封装了查询结果的Action Goldsoft Project :
 * QueryOperationLogListDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogListDTO {
  private String bizId = "";
  private String bizType = "";
  private String action = "";
  private String opTime = "";
  private String operator = "";
  private String credenceNum = "";
  private String office = "";
  private String credenceDate = "";
  private String credenceCharacterNum = "";
  public String getCredenceDate() {
    return credenceDate;
  }
  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }
  public String getCredenceNum() {
    return credenceNum;
  }
  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
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
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }
  public String getCredenceCharacterNum() {
    return credenceCharacterNum;
  }
  public void setCredenceCharacterNum(String credenceCharacterNum) {
    this.credenceCharacterNum = credenceCharacterNum;
  }
  
}
