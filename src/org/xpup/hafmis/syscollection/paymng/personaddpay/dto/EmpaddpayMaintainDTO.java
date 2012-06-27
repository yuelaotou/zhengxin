package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmpaddpayMaintainDTO implements Serializable{


  private static final long serialVersionUID = 7824999389240436586L;
  
  private String orgId="";
  private String orgName="";
  private String payMonth="";
  private String noteNum="";
  private String docNum="";
  private String opTime="";
  private String payStatus="";
  private String addPayYearMonth="";
  private String id="";
  private String personCounts="";
  private BigDecimal pay=new BigDecimal(0.00);
  private BigDecimal orgpay=new BigDecimal(0.00);
  private BigDecimal emppay=new BigDecimal(0.00);
  private String addPayDate=""; 
  private List list=new ArrayList();
  private String listCount="";
  
  private String tempPickType="";// ÌáÈ¡×´Ì¬ wangy 2008-02-26
  
  public String getTempPickType() {
    return tempPickType;
  }
  public void setTempPickType(String tempPickType) {
    this.tempPickType = tempPickType;
  }
  
  public String getListCount() {
    return listCount;
  }
  public void setListCount(String listCount) {
    this.listCount = listCount;
  }
  public String getAddPayDate() {
    return addPayDate;
  }
  public void setAddPayDate(String addPayDate) {
    this.addPayDate = addPayDate;
  }
  public BigDecimal getPay() {
    return pay;
  }
  public void setPay(BigDecimal pay) {
    this.pay = pay;
  }
 
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
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
  public String getPayMonth() {
    return payMonth;
  }
  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPersonCounts() {
    return personCounts;
  }
  public void setPersonCounts(String personCounts) {
    this.personCounts = personCounts;
  }
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
  public String getAddPayYearMonth() {
    return addPayYearMonth;
  }
  public void setAddPayYearMonth(String addPayYearMonth) {
    this.addPayYearMonth = addPayYearMonth;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public BigDecimal getEmppay() {
    return emppay;
  }
  public void setEmppay(BigDecimal emppay) {
    this.emppay = emppay;
  }
  public BigDecimal getOrgpay() {
    return orgpay;
  }
  public void setOrgpay(BigDecimal orgpay) {
    this.orgpay = orgpay;
  }
 

}
