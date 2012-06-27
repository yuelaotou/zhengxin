package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OrgInfoSearchAF extends ActionForm {
  private List list;

  private String officeCode = "";

  private String collectionBankId = "";

  private String character = "";

  private Map characterMap;

  private String deptInCharge = "";

  private Map deptInChargeMap;

  private String id = "";

  private String name = "";

  private String openStatus = "";

  private Map openStatusMap;

  private String payMode = "";

  private Map payModeMap;

  private String region = "";

  private Map regionMap;

  private String oldOrgId = "";

  private String inspector = "";

  private String payDate = "";

  private String code = "";

  private String openDateSta = "";

  private String openDateEnd = "";

  private int personc = 0;

  public int getPersonc() {
    return personc;
  }

  public void setPersonc(int personc) {
    this.personc = personc;
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    return null;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCollectionBankId() {
    return collectionBankId;
  }

  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }

  public String getDeptInCharge() {
    return deptInCharge;
  }

  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getInspector() {
    return inspector;
  }

  public void setInspector(String inspector) {
    this.inspector = inspector;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOldOrgId() {
    return oldOrgId;
  }

  public void setOldOrgId(String oldOrgId) {
    this.oldOrgId = oldOrgId;
  }

  public String getOpenStatus() {
    return openStatus;
  }

  public void setOpenStatus(String openStatus) {
    this.openStatus = openStatus;
  }

  public Map getOpenStatusMap() {
    return openStatusMap;
  }

  public void setOpenStatusMap(Map openStatusMap) {
    this.openStatusMap = openStatusMap;
  }

  public String getPayDate() {
    return payDate;
  }

  public void setPayDate(String payDate) {
    this.payDate = payDate;
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

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Map getCharacterMap() {
    return characterMap;
  }

  public void setCharacterMap(Map characterMap) {
    this.characterMap = characterMap;
  }

  public Map getDeptInChargeMap() {
    return deptInChargeMap;
  }

  public void setDeptInChargeMap(Map deptInChargeMap) {
    this.deptInChargeMap = deptInChargeMap;
  }

  public Map getRegionMap() {
    return regionMap;
  }

  public void setRegionMap(Map regionMap) {
    this.regionMap = regionMap;
  }

  public void clear() {
    this.officeCode = "";
    this.collectionBankId = "";
    this.character = "";
    this.deptInCharge = "";
    this.id = "";
    this.name = "";
    this.openStatus = "";
    this.payMode = "";
    this.region = "";
    this.oldOrgId = "";
    this.inspector = "";
    this.payDate = "";
    this.code = "";
    this.openDateSta = "";
    this.openDateEnd = "";
  }

  public String getOpenDateEnd() {
    return openDateEnd;
  }

  public void setOpenDateEnd(String openDateEnd) {
    this.openDateEnd = openDateEnd;
  }

  public String getOpenDateSta() {
    return openDateSta;
  }

  public void setOpenDateSta(String openDateSta) {
    this.openDateSta = openDateSta;
  }
}
