package org.xpup.hafmis.syscollection.paymng.personaddpay.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xpup.hafmis.common.form.CriterionsAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
/**
 * 
 * @author Â¬¸Ö
 *2007-6-28
 */
public class PersonAddPayMaintainAF extends CriterionsAF{
  

  private static final long serialVersionUID = 1L;

  public void reset() {
    // TODO Auto-generated method stub
   this.bizStatus="";
  }

  private List personAddPayList=new ArrayList();
  
  private BigDecimal orgPay=new BigDecimal(0.00);
  
  private BigDecimal empPay=new BigDecimal(0.00);
  
  private String beginMonth="";
  
  private String endMonth="";
  
  private String addPayReason="";
  
  private String orgId="";
  
  private String unitName="";
  
  private String docNumber="";
  
  private Integer personSum=new Integer(0);
  
  private BigDecimal empPaySum=new BigDecimal(0.00);
  
  private BigDecimal orgPaySum=new BigDecimal(0.00);

  private BigDecimal paySum=new BigDecimal(0.00);
  
  private String type="";
  
  private String listCount="";
  
  private Map sexMap=new HashMap();
  
  private String bizStatus="";
  
  private BigDecimal addPayAmount=new BigDecimal(0.00);
 
  private AddPayTail addPayTail=new AddPayTail();
  
  private Map bizStatusMap=new HashMap();
  
  private String money="";
  
  private String noteNum="";
  private String opTime="";
  private String payMonth = "";
  private String opTime1="";
  private String payMonth1 = "";
  
  private String collBankId = "";//¹é¼¯ÒøÐÐ
  
  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public String getOpTime() {
    return opTime;
  }

  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public Map getBizStatusMap() {
    return bizStatusMap;
  }

  public void setBizStatusMap(Map bizStatusMap) {
    this.bizStatusMap = bizStatusMap;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List getPersonAddPayList() {
    return personAddPayList;
  }

  public void setPersonAddPayList(List personAddPayList) {
    this.personAddPayList = personAddPayList;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public BigDecimal getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(BigDecimal empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(BigDecimal orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public BigDecimal getPaySum() {
    return paySum;
  }

  public void setPaySum(BigDecimal paySum) {
    this.paySum = paySum;
  }

  public Integer getPersonSum() {
    return personSum;
  }

  public void setPersonSum(Integer personSum) {
    this.personSum = personSum;
  }

  public String getAddPayReason() {
    return addPayReason;
  }

  public void setAddPayReason(String addPayReason) {
    this.addPayReason = addPayReason;
  }

  public String getBeginMonth() {
    return beginMonth;
  }

  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
  }

  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public String getEndMonth() {
    return endMonth;
  }

  public void setEndMonth(String endMonth) {
    this.endMonth = endMonth;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public AddPayTail getAddPayTail() {
    return addPayTail;
  }

  public void setAddPayTail(AddPayTail addPayTail) {
    this.addPayTail = addPayTail;
  }

  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public String getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(String bizStatus) {
    this.bizStatus = bizStatus;
  }

  public BigDecimal getAddPayAmount() {
    return addPayAmount;
  }

  public void setAddPayAmount(BigDecimal addPayAmount) {
    this.addPayAmount = addPayAmount;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  public String getOpTime1() {
    return opTime1;
  }

  public void setOpTime1(String opTime1) {
    this.opTime1 = opTime1;
  }

  public String getPayMonth1() {
    return payMonth1;
  }

  public void setPayMonth1(String payMonth1) {
    this.payMonth1 = payMonth1;
  }

 
}
