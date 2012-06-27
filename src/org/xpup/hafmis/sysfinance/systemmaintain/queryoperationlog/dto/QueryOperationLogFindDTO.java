package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto;
/**
 * Copy Right Information : 查询业务日封装了查询条件的DTO Goldsoft Project :
 * QueryOperationLogFindDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogFindDTO {
  /** 凭证类型 */
  private String credenceType = "";

  /** 操作员 */
  private String operator = "";

  /** 凭证日期开始 */
  private String credenceDateStart = "";

  /** 凭证日期结束 */
  private String credenceDateEnd = "";

  /** 动作 */
  private String action = "";

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getCredenceDateEnd() {
    return credenceDateEnd;
  }

  public void setCredenceDateEnd(String credenceDateEnd) {
    this.credenceDateEnd = credenceDateEnd;
  }

  public String getCredenceDateStart() {
    return credenceDateStart;
  }

  public void setCredenceDateStart(String credenceDateStart) {
    this.credenceDateStart = credenceDateStart;
  }

  public String getCredenceType() {
    return credenceType;
  }

  public void setCredenceType(String credenceType) {
    this.credenceType = credenceType;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
