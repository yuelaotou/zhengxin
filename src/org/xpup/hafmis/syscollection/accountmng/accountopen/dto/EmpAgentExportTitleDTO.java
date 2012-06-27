package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : 职工代扣导出-表头DTO Goldsoft Project :
 * EmpAgentExportTitleDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.13
 */
public class EmpAgentExportTitleDTO implements ExpDto {

  public String getInfo(String info) {
    if (info.equals("orgId")) {
      return this.orgId;

    }
    if (info.equals("orgName")) {
      return this.orgName;

    }
    return null;
  }

  private String orgId = "";

  private String orgName = "";

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
