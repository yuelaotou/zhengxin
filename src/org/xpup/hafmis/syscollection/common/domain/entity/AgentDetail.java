package org.xpup.hafmis.syscollection.common.domain.entity;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class AgentDetail extends DomainObject {
  /** 代扣年月 */
  private String agentYearMonth = "";

  /** 状态 */
  private String status = "";

  /** 操作员 */
  private String operator = "";

  /** 缴存方式 */
  private Integer payMode = new Integer(0);

  /** 代扣类型 */
  private Integer agentType = new Integer(0);

  /** 票据号 */
  private String noteNum = "";

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getAgentType() {
    return agentType;
  }

  public void setAgentType(Integer agentType) {
    this.agentType = agentType;
  }

  public Integer getPayMode() {
    return payMode;
  }

  public void setPayMode(Integer payMode) {
    this.payMode = payMode;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

}
