package org.xpup.hafmis.sysloan.loanapply.loancheck.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-09-22
 */
public class LoanCheckShowAF extends ActionForm {

  private static final long serialVersionUID = 847908045009297328L;

  private List list = null;

  private String id = null;

  private String contractId = null;

  private String borrowerName = null;

  private String cardNum = null;

  private List loanBankNameList = new ArrayList();
  
  private Map houseTypeMap = new HashMap();// 房屋类型下拉框

  private String beginBizDate = null;// 起始操作时间

  private String endBizDate = null;// 终止操作时间
  
  private String beginBackDate = null;// 起始回件日期

  private String endBackDate = null;// 终止回件日期

  private Map contractStMap = new HashMap();// 合同状态下拉框

  private String contractStFind = null;// 合同状态查询条件

  private String orgName = null;
  
  private String officeCode;

  private String loanBankName = null;// 放款银行

  private String houseType = null;// 购房类型

  private String contractSt = null;

  private Integer count = new Integer(0);

  private BigDecimal loanTotleMoney = new BigDecimal(0.00);// 借款金额-总额
  //wuht
  private BigDecimal totlePriceAll = new BigDecimal(0.00);// 合计房价
  
  private BigDecimal houseAreaAll = new BigDecimal(0.00);// 合计建筑面积
  
  private BigDecimal loanTotleMoneyYearSum = new BigDecimal(0.00);// 借款金额-总额当年累计

  private BigDecimal totlePriceAllYearSum = new BigDecimal(0.00);// 合计房价当年累计
  
  private BigDecimal houseAreaAllYearSum = new BigDecimal(0.00);// 合计建筑面积当年累计
  
  private List listAll = null;
 
 

  public List getListAll() {
    return listAll;
  }

  public void setListAll(List listAll) {
    this.listAll = listAll;
  }

  public BigDecimal getHouseAreaAll() {
    return houseAreaAll;
  }

  public void setHouseAreaAll(BigDecimal houseAreaAll) {
    this.houseAreaAll = houseAreaAll;
  }

  public BigDecimal getTotlePriceAll() {
    return totlePriceAll;
  }

  public void setTotlePriceAll(BigDecimal totlePriceAll) {
    this.totlePriceAll = totlePriceAll;
  }

  public String getContractStFind() {
    return contractStFind;
  }

  public void setContractStFind(String contractStFind) {
    this.contractStFind = contractStFind;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public Map getContractStMap() {
    return contractStMap;
  }

  public void setContractStMap(Map contractStMap) {
    this.contractStMap = contractStMap;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
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

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
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

  public List getList() {
    return list;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public BigDecimal getLoanTotleMoney() {
    return loanTotleMoney;
  }

  public void setLoanTotleMoney(BigDecimal loanTotleMoney) {
    this.loanTotleMoney = loanTotleMoney;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getHouseAreaAllYearSum() {
    return houseAreaAllYearSum;
  }

  public void setHouseAreaAllYearSum(BigDecimal houseAreaAllYearSum) {
    this.houseAreaAllYearSum = houseAreaAllYearSum;
  }

  public BigDecimal getLoanTotleMoneyYearSum() {
    return loanTotleMoneyYearSum;
  }

  public void setLoanTotleMoneyYearSum(BigDecimal loanTotleMoneyYearSum) {
    this.loanTotleMoneyYearSum = loanTotleMoneyYearSum;
  }

  public BigDecimal getTotlePriceAllYearSum() {
    return totlePriceAllYearSum;
  }

  public void setTotlePriceAllYearSum(BigDecimal totlePriceAllYearSum) {
    this.totlePriceAllYearSum = totlePriceAllYearSum;
  }

  public String getBeginBackDate() {
    return beginBackDate;
  }

  public void setBeginBackDate(String beginBackDate) {
    this.beginBackDate = beginBackDate;
  }

  public String getEndBackDate() {
    return endBackDate;
  }

  public void setEndBackDate(String endBackDate) {
    this.endBackDate = endBackDate;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

}
