package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
public class CollFnComparisonOrgAccountShowAF extends ActionForm {
  private String officeCode="";
  private String bankId="";
  private String orgId="";
  private String orgName="";
  private String collSt="";
  private String fnSt="";
  private String timeSt="";
  private String timeEnd="";
  private String cintractId="";
  private String contractIdEnd="";
  private Map credenceStMap = null;
  private Map map = null;
  List list=new ArrayList();
  
  //增加打印条件弹出框
  private String orgidst="";
  private String orgidend="";
  private String contractId="";

  public String getCollSt() {
    return collSt;
  }
  public void setCollSt(String collSt) {
    this.collSt = collSt;
  }
  public String getFnSt() {
    return fnSt;
  }
  public void setFnSt(String fnSt) {
    this.fnSt = fnSt;
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
  public String getTimeEnd() {
    return timeEnd;
  }
  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
  public String getTimeSt() {
    return timeSt;
  }
  public void setTimeSt(String timeSt) {
    this.timeSt = timeSt;
  }
  public void reset(){
    officeCode="";
    bankId="";
    orgId="";
    orgName="";
    collSt="";
    fnSt="";
    timeSt="";
    timeEnd="";
  }
  public Map getCredenceStMap() {
    return credenceStMap;
  }
  public void setCredenceStMap(Map credenceStMap) {
    this.credenceStMap = credenceStMap;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public String getOrgidend() {
    return orgidend;
  }
  public void setOrgidend(String orgidend) {
    this.orgidend = orgidend;
  }
  public String getOrgidst() {
    return orgidst;
  }
  public void setOrgidst(String orgidst) {
    this.orgidst = orgidst;
  }
  public String getCintractId() {
    return cintractId;
  }
  public void setCintractId(String cintractId) {
    this.cintractId = cintractId;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getContractIdEnd() {
    return contractIdEnd;
  }
  public void setContractIdEnd(String contractIdEnd) {
    this.contractIdEnd = contractIdEnd;
  }

  
}
