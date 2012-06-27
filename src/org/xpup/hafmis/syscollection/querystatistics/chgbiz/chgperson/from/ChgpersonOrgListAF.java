package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChgpersonOrgListAF extends ActionForm{

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    
    this.orgId = "";//单位编号
    this.orgName = "";// 单位名称
    this.officeCode="";//办事处
    this.collectionBank="";//归集银行
    this.chgMonthStart = "";//变更年月开始
    this.chgMonthEnd = "";//变更年月结束
    this.chgDateStart = "";//变更日期开始
    this.chgDateEnd = "";//变更日期结束
    this.chgStatus=new Integer(0);//状态
    this.map=null;
    this.list=null;
  }

  private static final long serialVersionUID = 1L;
  private String officeCode = "";//办事处
  private String collectionBank = "";//归集银行
  private String orgId = "";//单位编号
  private String orgName = "";// 单位名称
  private String chgMonthStart = "";//变更年月开始
  private String chgMonthEnd = "";//变更年月结束
  private String chgDateStart = "";//变更日期开始
  private String chgDateEnd = "";//变更日期结束
  private Map map = null;
  private Integer chgStatus = new Integer(0);//状态
  private List list = null;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getChgDateEnd() {
    return chgDateEnd;
  }

  public void setChgDateEnd(String chgDateEnd) {
    this.chgDateEnd = chgDateEnd;
  }

  public String getChgDateStart() {
    return chgDateStart;
  }

  public void setChgDateStart(String chgDateStart) {
    this.chgDateStart = chgDateStart;
  }

  public String getChgMonthEnd() {
    return chgMonthEnd;
  }

  public void setChgMonthEnd(String chgMonthEnd) {
    this.chgMonthEnd = chgMonthEnd;
  }

  public String getChgMonthStart() {
    return chgMonthStart;
  }

  public void setChgMonthStart(String chgMonthStart) {
    this.chgMonthStart = chgMonthStart;
  }

  public Integer getChgStatus() {
    return chgStatus;
  }

  public void setChgStatus(Integer chgStatus) {
    this.chgStatus = chgStatus;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
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
