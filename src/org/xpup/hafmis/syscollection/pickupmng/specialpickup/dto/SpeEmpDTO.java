package org.xpup.hafmis.syscollection.pickupmng.specialpickup.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class SpeEmpDTO implements Serializable{
  private String id;
  private String orgid;
  private String orginfoname;
  private String empid;
  private String empinfoname;
  private String officecode;
  private String collectionBankId;
  private String pickCorpus;
  private String operateTime;
  private String operator;
  private String isPick;
  private String pickPeopleNum;
  private String pickCorpusSum;
  private List list;
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpinfoname() {
    return empinfoname;
  }
  public void setEmpinfoname(String empinfoname) {
    this.empinfoname = empinfoname;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getIsPick() {
    return isPick;
  }
  public void setIsPick(String isPick) {
    this.isPick = isPick;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getOperateTime() {
    return operateTime;
  }
  public void setOperateTime(String operateTime) {
    this.operateTime = operateTime;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrginfoname() {
    return orginfoname;
  }
  public void setOrginfoname(String orginfoname) {
    this.orginfoname = orginfoname;
  }
  public String getPickCorpus() {
    return pickCorpus;
  }
  public void setPickCorpus(String pickCorpus) {
    this.pickCorpus = pickCorpus;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getPickCorpusSum() {
    return pickCorpusSum;
  }
  public void setPickCorpusSum(String pickCorpusSum) {
    this.pickCorpusSum = pickCorpusSum;
  }
  public String getPickPeopleNum() {
    return pickPeopleNum;
  }
  public void setPickPeopleNum(String pickPeopleNum) {
    this.pickPeopleNum = pickPeopleNum;
  }
  
}
