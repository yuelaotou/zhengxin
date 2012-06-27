package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PrincipalglTaAF extends ActionForm{
  
  private String mydate = "";
  private String id = "";
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
  
  private BigDecimal thisYearDebitSum = new BigDecimal(0.00);//本期借方发生额
  private BigDecimal thisYearLenderSum = new BigDecimal(0.00);//本期贷方发生额
  private int thisYearDebitDegree = 0;//本期借方笔数
  private int thisYearLenderDegree = 0;//本期贷方笔数

  public List getPrintlist() {
    return printlist;
  }

  public void setPrintlist(List printlist) {
    this.printlist = printlist;
  }

  public int getThisYearDebitDegree() {
    return thisYearDebitDegree;
  }

  public void setThisYearDebitDegree(int thisYearDebitDegree) {
    this.thisYearDebitDegree = thisYearDebitDegree;
  }

  public BigDecimal getThisYearDebitSum() {
    return thisYearDebitSum;
  }

  public void setThisYearDebitSum(BigDecimal thisYearDebitSum) {
    this.thisYearDebitSum = thisYearDebitSum;
  }

  public int getThisYearLenderDegree() {
    return thisYearLenderDegree;
  }

  public void setThisYearLenderDegree(int thisYearLenderDegree) {
    this.thisYearLenderDegree = thisYearLenderDegree;
  }

  public BigDecimal getThisYearLenderSum() {
    return thisYearLenderSum;
  }

  public void setThisYearLenderSum(BigDecimal thisYearLenderSum) {
    this.thisYearLenderSum = thisYearLenderSum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFloorid() {
    return floorid;
  }

  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
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

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getRadioValue() {
    return radioValue;
  }

  public void setRadioValue(String radioValue) {
    this.radioValue = radioValue;
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
