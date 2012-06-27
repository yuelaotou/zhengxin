package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

/**
 * Copy Right Information : 职工代扣导入-表头DTO Goldsoft Project :
 * EmpAgentImportTitleDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.13
 */
public class EmpAgentImportTitleDTO extends impDto {
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
