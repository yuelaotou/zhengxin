package org.xpup.hafmis.syscollection.accountmng.accountchg.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;

public class OrgChgAF  extends ValidatorForm {
  private Org org = new Org();
  private OrgChgLog orgChgLog = new OrgChgLog();
  private Map orgchgtypeMap=new HashMap();
  private String payMode="";
  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }

  public Org getOrg() {
    return org;
  }

  public OrgChgLog getOrgChgLog() {
    return orgChgLog;
  }

  public void setOrgChgLog(OrgChgLog orgChgLog) {
    this.orgChgLog = orgChgLog;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public Map getOrgchgtypeMap() {
    return orgchgtypeMap;
  }

  public void setOrgchgtypeMap(Map orgchgtypeMap) {
    this.orgchgtypeMap = orgchgtypeMap;
  }
}
