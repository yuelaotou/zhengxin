package org.xpup.hafmis.syscollection.accountmng.accountopen.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OrgkhCriteronsAF extends ActionForm {
  private static final long serialVersionUID = 157830469042818336L;

  private Map statusMap = new HashMap();

  private Map payModeMap = new HashMap();

  private String status="";
  
  private String payMode="";
  
  private String orgid = null;

  private String name = null;
  
  private String startdate="";
  
  private String enddate="";
  
  /**
   * 付云峰修改：判断排序时的状态是否要对条件查询出的结果进行判断。
   */
  private String type = "";
  
private int listCount;
  public int getListCount() {
  return listCount;
}

public void setListCount(int listCount) {
  this.listCount = listCount;
}


  public String getOrgid() {
  return orgid;
}

public void setOrgid(String orgid) {
  this.orgid = orgid;
}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }

  public Map getPayModeMap() {
    return payModeMap;
  }

  public void setPayModeMap(Map payModeMap) {
    this.payModeMap = payModeMap;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Map getStatusMap() {
    return statusMap;
  }

  public void setStatusMap(Map statusMap) {
    this.statusMap = statusMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void reset() {
    this.orgid = "";
    this.name = "";
    this.payMode = "";
    this.status = "";
    this.startdate = "";
    this.enddate = "";
  }

  public String getEnddate() {
    return enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String getStartdate() {
    return startdate;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }


}
