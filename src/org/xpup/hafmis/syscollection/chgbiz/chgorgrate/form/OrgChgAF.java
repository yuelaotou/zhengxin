package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

public class OrgChgAF extends ActionForm {
  private static final long serialVersionUID = 1L;

  private String orgid = "";

  private String orgname = "";

  private String orgaddress = "";

  private String orgdep = "";

  private String orgcharacter = "";

  private String orgtransactorname = "";

  private String orgtransactortelephone = "";

  private String orgcount = "";

  private OrgChg orgChg = new OrgChg();

  private List list = new ArrayList();

  public OrgChg getOrgChg() {
    return orgChg;
  }

  public void setOrgChg(OrgChg orgChg) {
    this.orgChg = orgChg;
  }

  public String getOrgaddress() {
    return orgaddress;
  }

  public void setOrgaddress(String orgaddress) {
    this.orgaddress = orgaddress;
  }

  public String getOrgcharacter() {
    return orgcharacter;
  }

  public void setOrgcharacter(String orgcharacter) {
    this.orgcharacter = orgcharacter;
  }

  public String getOrgcount() {
    return orgcount;
  }

  public void setOrgcount(String orgcount) {
    this.orgcount = orgcount;
  }

  public String getOrgdep() {
    return orgdep;
  }

  public void setOrgdep(String orgdep) {
    this.orgdep = orgdep;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getOrgtransactorname() {
    return orgtransactorname;
  }

  public void setOrgtransactorname(String orgtransactorname) {
    this.orgtransactorname = orgtransactorname;
  }

  public String getOrgtransactortelephone() {
    return orgtransactortelephone;
  }

  public void setOrgtransactortelephone(String orgtransactortelephone) {
    this.orgtransactortelephone = orgtransactortelephone;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}