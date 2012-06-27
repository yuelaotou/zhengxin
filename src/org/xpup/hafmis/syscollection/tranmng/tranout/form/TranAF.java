package org.xpup.hafmis.syscollection.tranmng.tranout.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class TranAF extends ActionForm {
  
  private List list = null;
  BigDecimal maxSum = new BigDecimal(0.00);
  private String id = null;
  private String orgInfoId = null;
  private String name = null; // EmpName

  private String outOrgId= null;
  private String outOrgname = null;
  private String inOrgId = null;
  private String inOrgName = null;
  private String noteNumber = null;
  private String headId="";
  private String sumBalance = "";
  private String sumInterest ="";
  private String sumMoney = "" ;
  private String tranStatus = "";
  private String docNum = "";
  private String headid="";
  private String type="";
  
  private String card_num = null;
  private String month_income = null;
  private String pkid = null;
  private BigDecimal salaryBase_sum = new BigDecimal(0.0);
  
  private String temp_pick="";//�ύ״̬
  
  
  
  public String getTranStatus() {
    return tranStatus;
  }
  public void setTranStatus(String tranStatus) {
    this.tranStatus = tranStatus;
  }
  public String getInOrgId() {
    return inOrgId;
  }
  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getOrgInfoId() {
    return orgInfoId;
  }
  public void setOrgInfoId(String orgInfoId) {
    this.orgInfoId = orgInfoId;
  }

  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getCard_num() {
    return card_num;
  }
  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }
  public String getMonth_income() {
    return month_income;
  }
  public void setMonth_income(String month_income) {
    this.month_income = month_income;
  }

  public String getInOrgName() {
    return inOrgName;
  }
  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }
  public String getNoteNumber() {
    return noteNumber;
  }
  public void setNoteNumber(String noteNumber) {
    this.noteNumber = noteNumber;
  }

  public String getOutOrgname() {
    return outOrgname;
  }
  public void setOutOrgname(String outOrgname) {
    this.outOrgname = outOrgname;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPkid() {
    return pkid;
  }
  public void setPkid(String pkid) {
    this.pkid = pkid;
  }
  public BigDecimal getMaxSum() {
    return maxSum;
  }
  public void setMaxSum(BigDecimal maxSum) {
    this.maxSum = maxSum;
  }
  public BigDecimal getSalaryBase_sum() {
    return salaryBase_sum;
  }
  public void setSalaryBase_sum(BigDecimal salaryBase_sum) {
    this.salaryBase_sum = salaryBase_sum;
  }
  public String getOutOrgId() {
    return outOrgId;
  }
  public void setOutOrgId(String outOrgId) {
    this.outOrgId = outOrgId;
  }
  public String getHeadId() {
    return headId;
  }
  public void setHeadId(String headId) {
    this.headId = headId;
  }
  public String getSumBalance() {
    return sumBalance;
  }
  public void setSumBalance(String sumBalance) {
    this.sumBalance = sumBalance;
  }
  public String getSumInterest() {
    return sumInterest;
  }
  public void setSumInterest(String sumInterest) {
    this.sumInterest = sumInterest;
  }
  public String getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(String sumMoney) {
    this.sumMoney = sumMoney;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getHeadid() {
    return headid;
  }
  public void setHeadid(String headid) {
    this.headid = headid;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getTemp_pick() {
    return temp_pick;
  }
  public void setTemp_pick(String temp_pick) {
    this.temp_pick = temp_pick;
  }


}
