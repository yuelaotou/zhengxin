package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-09-22
 */
public class LoanFirstCheckShowAF extends ActionForm {

  private static final long serialVersionUID = 847908045009297328L;

  private List list = null;

  private String contractId = null;

  private String borrowerName = null;

  private String cardNum = null;

  private Map houseTypeMap = new HashMap();// 房屋类型下拉框

  private String opTimeSt = null;// 起始操作时间

  private String opTimeEnd = null;// 终止操作时间
  
  private Map contractStMap = new HashMap();// 合同状态下拉框

  private String contractSt = null;// 合同状态查询条件

  private String orgName = null;
  
  private String officeCode;

  private String houseType = null;// 购房类型

  private Integer count = new Integer(0);

  private BigDecimal loanMoneyTotal = new BigDecimal(0.00);
  
  private BigDecimal housePriceTotal = new BigDecimal(0.00);
  
  private BigDecimal houseAreaTotal = new BigDecimal(0.00);

  public static long getSerialVersionUID() {
    return serialVersionUID;
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

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public Map getContractStMap() {
    return contractStMap;
  }

  public void setContractStMap(Map contractStMap) {
    this.contractStMap = contractStMap;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getHouseAreaTotal() {
    return houseAreaTotal;
  }

  public void setHouseAreaTotal(BigDecimal houseAreaTotal) {
    this.houseAreaTotal = houseAreaTotal;
  }

  public BigDecimal getHousePriceTotal() {
    return housePriceTotal;
  }

  public void setHousePriceTotal(BigDecimal housePriceTotal) {
    this.housePriceTotal = housePriceTotal;
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

  public void setList(List list) {
    this.list = list;
  }

  public BigDecimal getLoanMoneyTotal() {
    return loanMoneyTotal;
  }

  public void setLoanMoneyTotal(BigDecimal loanMoneyTotal) {
    this.loanMoneyTotal = loanMoneyTotal;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOpTimeEnd() {
    return opTimeEnd;
  }

  public void setOpTimeEnd(String opTimeEnd) {
    this.opTimeEnd = opTimeEnd;
  }

  public String getOpTimeSt() {
    return opTimeSt;
  }

  public void setOpTimeSt(String opTimeSt) {
    this.opTimeSt = opTimeSt;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  

}
