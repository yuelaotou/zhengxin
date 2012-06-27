package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;
/**
 * Copy Right Information : 单位代扣导入DTO Goldsoft Project :
 * OrgAgentImportDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.12
 */
public class OrgAgentImportDTO extends impDto {
  private String orgId = "";

  private String orgName = "";

  private String orgAgentNum = "";

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
