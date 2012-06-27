package org.xpup.hafmis.syscollection.accountmng.accountchg.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class OrgChgListAF extends ActionForm {

  private static final long serialVersionUID = 157830469042818336L;

  private Map orgchgtypeMap = new HashMap();

  private String chgType = "";

  private String id = null;

  private String name = null;

  private List list = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChgType() {
    return chgType;
  }

  public void setChgType(String chgType) {
    this.chgType = chgType;
  }

  public Map getOrgchgtypeMap() {
    return orgchgtypeMap;
  }

  public void setOrgchgtypeMap(Map orgchgtypeMap) {
    this.orgchgtypeMap = orgchgtypeMap;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

}
