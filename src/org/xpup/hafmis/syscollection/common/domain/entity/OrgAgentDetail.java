package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;

public class OrgAgentDetail {
  /** 单位代扣清册id */
  private Serializable orgAgentId;

  /** 单位代扣号 */
  private String orgAgentNum = "";

  /** 代扣清册id */
  private Integer agentHeadId = new Integer(0);

  public Integer getAgentHeadId() {
    return agentHeadId;
  }

  public void setAgentHeadId(Integer agentHeadId) {
    this.agentHeadId = agentHeadId;
  }

  public Serializable getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(Serializable orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }
}
