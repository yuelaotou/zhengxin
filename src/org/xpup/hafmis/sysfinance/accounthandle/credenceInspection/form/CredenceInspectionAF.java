package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;

public class CredenceInspectionAF extends ActionForm {
  private String type = "0";

  private CredenceInspectionFindDTO credenceInspectionFindDTO = new CredenceInspectionFindDTO();

  private String credenceSt = "";

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CredenceInspectionFindDTO getCredenceInspectionFindDTO() {
    return credenceInspectionFindDTO;
  }

  public void setCredenceInspectionFindDTO(
      CredenceInspectionFindDTO credenceInspectionFindDTO) {
    this.credenceInspectionFindDTO = credenceInspectionFindDTO;
  }


}
