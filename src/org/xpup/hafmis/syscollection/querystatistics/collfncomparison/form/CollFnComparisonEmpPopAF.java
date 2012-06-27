package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CollFnComparisonEmpPopAF extends ActionForm {
  private String orgId = "";

  private String orgName = "";

  private String date = "";

  private String bizType = "";

  private String docNum = "";

  private List list;

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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
