package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class SearchLackInfoListAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String officeCode = "";// 办事处

  private String collectionBank = "";// 归集银行

  private String natureOfUnits = "";// 单位性质

  private Map natureOfUnits_1 = new HashMap();// 单位性质

  private String authorities = "";// 主管部门

  private Map authorities_1 = new HashMap();// 主管部门

  private String orgId = "";// 单位编号

  private String orgName = "";// 单位名称

  private String chgMonthStart = "";// 发生月数开始

  private String chgMonthEnd = "";// 发生月数结束

  private String inArea = "";// 所在地区

  private Map inArea_1 = new HashMap();// 所在地区

  private String yearMonth = "";// 欠缴年月

  private List list;

  // -----------------查询OLD 营口欠缴数据使用,
  private String orgId_old_q = "";

  private String orgName_old_q = "";

  private String yearMonth_old_q = "";

  private String orgid_old = "";

  private String orgname_old = "";

  private String lack_month_old = "";

  private String lack_pay_old = "";

  private String lack_status_old = "";

  private String orgratebeg = "";

  private String orgrateend = "";

  private String empratebeg = "";

  private String emprateend = "";

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getAuthorities() {
    return authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }

  public Map getAuthorities_1() {
    return authorities_1;
  }

  public void setAuthorities_1(Map authorities_1) {
    this.authorities_1 = authorities_1;
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

  public String getInArea() {
    return inArea;
  }

  public void setInArea(String inArea) {
    this.inArea = inArea;
  }

  public Map getInArea_1() {
    return inArea_1;
  }

  public void setInArea_1(Map inArea_1) {
    this.inArea_1 = inArea_1;
  }

  public String getNatureOfUnits() {
    return natureOfUnits;
  }

  public void setNatureOfUnits(String natureOfUnits) {
    this.natureOfUnits = natureOfUnits;
  }

  public Map getNatureOfUnits_1() {
    return natureOfUnits_1;
  }

  public void setNatureOfUnits_1(Map natureOfUnits_1) {
    this.natureOfUnits_1 = natureOfUnits_1;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
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

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public String getLack_month_old() {
    return lack_month_old;
  }

  public void setLack_month_old(String lack_month_old) {
    this.lack_month_old = lack_month_old;
  }

  public String getLack_pay_old() {
    return lack_pay_old;
  }

  public void setLack_pay_old(String lack_pay_old) {
    this.lack_pay_old = lack_pay_old;
  }

  public String getLack_status_old() {
    return lack_status_old;
  }

  public void setLack_status_old(String lack_status_old) {
    this.lack_status_old = lack_status_old;
  }

  public String getOrgid_old() {
    return orgid_old;
  }

  public void setOrgid_old(String orgid_old) {
    this.orgid_old = orgid_old;
  }

  public String getOrgId_old_q() {
    return orgId_old_q;
  }

  public void setOrgId_old_q(String orgId_old_q) {
    this.orgId_old_q = orgId_old_q;
  }

  public String getOrgname_old() {
    return orgname_old;
  }

  public void setOrgname_old(String orgname_old) {
    this.orgname_old = orgname_old;
  }

  public String getOrgName_old_q() {
    return orgName_old_q;
  }

  public void setOrgName_old_q(String orgName_old_q) {
    this.orgName_old_q = orgName_old_q;
  }

  public String getYearMonth_old_q() {
    return yearMonth_old_q;
  }

  public void setYearMonth_old_q(String yearMonth_old_q) {
    this.yearMonth_old_q = yearMonth_old_q;
  }

  public String getEmpratebeg() {
    return empratebeg;
  }

  public void setEmpratebeg(String empratebeg) {
    this.empratebeg = empratebeg;
  }

  public String getEmprateend() {
    return emprateend;
  }

  public void setEmprateend(String emprateend) {
    this.emprateend = emprateend;
  }

  public String getOrgratebeg() {
    return orgratebeg;
  }

  public void setOrgratebeg(String orgratebeg) {
    this.orgratebeg = orgratebeg;
  }

  public String getOrgrateend() {
    return orgrateend;
  }

  public void setOrgrateend(String orgrateend) {
    this.orgrateend = orgrateend;
  }

}
