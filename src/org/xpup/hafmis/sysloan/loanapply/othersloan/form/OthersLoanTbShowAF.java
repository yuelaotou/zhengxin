package org.xpup.hafmis.sysloan.loanapply.othersloan.form;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
public class OthersLoanTbShowAF extends ActionForm {

  private static final long serialVersionUID = -7005677950157431557L;
  
  private String name="";
  
  private String empId="";
  
  private String orgName="";
  
  private String  orgId="";
  
  private String office="";
  
  private String cardNum="";
  
  private String binDate="";
  
  private String endDate="";
  
  BigDecimal totalLoanMoney=new BigDecimal(0.00);
  
  BigDecimal totalHouseArea=new BigDecimal(0.00);
  
  int totalPerson=0;
  
  BigDecimal totalHousePrice=new BigDecimal(0.00);
  
  private List list=new ArrayList();
  
  private List allList=new ArrayList(); //打印的list
  
  private List allList_1=new ArrayList();//显示的总list
  
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getBinDate() {
    return binDate;
  }
  public void setBinDate(String binDate) {
    this.binDate = binDate;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
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
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public List getAllList() {
    return allList;
  }
  public void setAllList(List allList) {
    this.allList = allList;
  }
  public BigDecimal getTotalHouseArea() {
    return totalHouseArea;
  }
  public void setTotalHouseArea(BigDecimal totalHouseArea) {
    this.totalHouseArea = totalHouseArea;
  }
  public BigDecimal getTotalHousePrice() {
    return totalHousePrice;
  }
  public void setTotalHousePrice(BigDecimal totalHousePrice) {
    this.totalHousePrice = totalHousePrice;
  }
  public BigDecimal getTotalLoanMoney() {
    return totalLoanMoney;
  }
  public void setTotalLoanMoney(BigDecimal totalLoanMoney) {
    this.totalLoanMoney = totalLoanMoney;
  }
  public int getTotalPerson() {
    return totalPerson;
  }
  public void setTotalPerson(int totalPerson) {
    this.totalPerson = totalPerson;
  }
  public List getAllList_1() {
    return allList_1;
  }
  public void setAllList_1(List allList_1) {
    this.allList_1 = allList_1;
  }
 
}
