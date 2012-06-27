package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : 单位代扣导出DTO Goldsoft Project :
 * OrgAgentExportDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.12
 */
public class OrgAgentExportDTO implements ExpDto {
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

  public String getInfo(String info) {
    if (info.equals("orgId")) {
      return this.orgId;
    }
    if (info.equals("orgName")) {
      return this.orgName;
    }
    if (info.equals("orgAgentNum")) {
      return this.orgAgentNum;
    }
    return null;

  }
}
