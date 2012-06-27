package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InterestglTaAF extends ActionForm{
  private String id = "";
  private String mydate = "";

  private String radioValue = "";//单选按钮
  private String office = "";//办事处
  private String loanBank = "";//放款银行
  private String developerName = "";//开发商
  private String floorNum = "";//楼盘
  private String assistantOrgName = "";//担保公司
  private String startDate = "";//查询开始时间
  private String endDate = "";//查询结束时间
  
  private String buyhouseorgid = "";//隐藏域
  private String floorid = "";//隐藏域
  private List list = new ArrayList();
  private List printlist = new ArrayList();
  private List officeList = new ArrayList();
  private List loanBankNameList = new ArrayList();
  private List assistantOrgNameList = new ArrayList();
  
  private String sumInterest = "";//合计本期利息
  private String sumOverdueInterest = "";//合计逾期利息
  
  public List getPrintlist() {
    return printlist;
  }
  public void setPrintlist(List printlist) {
    this.printlist = printlist;
  }
  public String getSumInterest() {
    return sumInterest;
  }
  public void setSumInterest(String sumInterest) {
    this.sumInterest = sumInterest;
  }
  public String getSumOverdueInterest() {
    return sumOverdueInterest;
  }
  public void setSumOverdueInterest(String sumOverdueInterest) {
    this.sumOverdueInterest = sumOverdueInterest;
  }
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }
  public List getAssistantOrgNameList() {
    return assistantOrgNameList;
  }
  public void setAssistantOrgNameList(List assistantOrgNameList) {
    this.assistantOrgNameList = assistantOrgNameList;
  }
  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }
  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }
  public String getDeveloperName() {
    return developerName;
  }
  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getFloorid() {
    return floorid;
  }
  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }
  public String getFloorNum() {
    return floorNum;
  }
  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getLoanBank() {
    return loanBank;
  }
  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }
  public List getLoanBankNameList() {
    return loanBankNameList;
  }
  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public List getOfficeList() {
    return officeList;
  }
  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }
  public String getRadioValue() {
    return radioValue;
  }
  public void setRadioValue(String radioValue) {
    this.radioValue = radioValue;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.radioValue = "";//单选按钮
    this.office = "";//办事处
    this.loanBank = "";//放款银行
    this.developerName = "";//开发商
    this.floorNum = "";//楼盘
    this.assistantOrgName = "";//担保公司
    this.startDate = "";//查询开始时间
    this.endDate = "";//查询结束时间
//    this.loanassistantorgId = "";//担保公司编号
    this.buyhouseorgid = "";//隐藏域
    this.floorid = "";//隐藏域
  }
  public String getMydate() {
    return mydate;
  }
  public void setMydate(String mydate) {
    this.mydate = mydate;
  }
}
