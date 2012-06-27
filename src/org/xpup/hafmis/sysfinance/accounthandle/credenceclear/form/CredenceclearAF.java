package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;

public class CredenceclearAF extends ActionForm {
  private String type = "0";

  private List list = new ArrayList();

  private CredencecheckFindDTO credencecheckFindDTO = new CredencecheckFindDTO();

  private Map credenceStMap = null;

  private String credenceSt = "";

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getCredenceStMap() {
    return credenceStMap;
  }

  public void setCredenceStMap(Map credenceStMap) {
    this.credenceStMap = credenceStMap;
  }

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

  public CredencecheckFindDTO getCredencecheckFindDTO() {
    return credencecheckFindDTO;
  }

  public void setCredencecheckFindDTO(CredencecheckFindDTO credencecheckFindDTO) {
    this.credencecheckFindDTO = credencecheckFindDTO;
  }
}
