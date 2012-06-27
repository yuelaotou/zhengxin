package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

//wuht
//1、查询条件

public class QueryPayOffRecordsTaShowAF extends ActionForm {
  
  private static final long serialVersionUID = 847908045009297328L;

  private List list = null;
  
  private List listAll=null;

  // 办事处，银行，发放日期，还清日期，合同编号，姓名，身份证号，
  private String contractId = null;

  private String borrowerName = null;

  private String cardNum = null;

  private String office = null;

  private String loanBankName = null;

  private String loanStartDate = null;
  
  private String loanEndDate = null;
  
  private String loanPayOffDate = null;
  
  private String loanPayOffEndDate = null;
  
  private String houseType="";
  
  private  Map houseTypeMap=new HashMap();

  // 合计：人数，贷款金额，利息总额，还款总额
  private String peopleNumSum = "";

  private String loanMoneySum = "";

  private String interestSum = "";

  private String corpusSum = "";

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.office = "";
    this.loanStartDate = "";
    this.loanPayOffDate = "";
    this.loanBankName = "";
    

  }

 

  public String getLoanBankName() {
    return loanBankName;
  }



  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }



  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }



  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanPayOffDate() {
    return loanPayOffDate;
  }

  public void setLoanPayOffDate(String loanPayOffDate) {
    this.loanPayOffDate = loanPayOffDate;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getCorpusSum() {
    return corpusSum;
  }

  public void setCorpusSum(String corpusSum) {
    this.corpusSum = corpusSum;
  }

  public String getInterestSum() {
    return interestSum;
  }

  public void setInterestSum(String interestSum) {
    this.interestSum = interestSum;
  }

  public String getLoanMoneySum() {
    return loanMoneySum;
  }

  public void setLoanMoneySum(String loanMoneySum) {
    this.loanMoneySum = loanMoneySum;
  }

  public String getPeopleNumSum() {
    return peopleNumSum;
  }

  public void setPeopleNumSum(String peopleNumSum) {
    this.peopleNumSum = peopleNumSum;
  }

  public List getListAll() {
    return listAll;
  }

  public void setListAll(List listAll) {
    this.listAll = listAll;
  }



  public String getLoanEndDate() {
    return loanEndDate;
  }



  public void setLoanEndDate(String loanEndDate) {
    this.loanEndDate = loanEndDate;
  }



  public String getLoanPayOffEndDate() {
    return loanPayOffEndDate;
  }



  public void setLoanPayOffEndDate(String loanPayOffEndDate) {
    this.loanPayOffEndDate = loanPayOffEndDate;
  }



  public String getHouseType() {
    return houseType;
  }



  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }



  public Map getHouseTypeMap() {
    return houseTypeMap;
  }



  public void setHouseTypeMap(Map houseTypeMap) {
    this.houseTypeMap = houseTypeMap;
  }

}
