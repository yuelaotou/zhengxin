package org.xpup.hafmis.syscollection.paymng.agent.dto;

/**
 * Copy Right Information : 封装做过人员变更职工信息的DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.21
 */
public class PersonChgInfoDTO {
  /** 人员变更的头表id */
  private String id = "";

  /** 单位id */
  private String orgId = "";

  /** 职工id */
  private String empId = "";

  /** 变更前状态s */
  private String chgType = "";

  private String oldPayStatus = "";

  public String getOldPayStatus() {
    return oldPayStatus;
  }

  public void setOldPayStatus(String oldPayStatus) {
    this.oldPayStatus = oldPayStatus;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getChgType() {
    return chgType;
  }

  public void setChgType(String chgType) {
    this.chgType = chgType;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
}
